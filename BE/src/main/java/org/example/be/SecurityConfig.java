package org.example.be;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.be.entity.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    // Demo key (>= 32 chars)
    private static final String SECRET = "12345678901234567890123456789012";

    // ===== 1) Encode password =====
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ===== 2) Load user từ DB =====
    @Bean
    public UserDetailsService userDetailsService(org.example.be.repository.CustomerRepository repo) {
        return username -> {
            Customer c = repo.findById(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            // ROLE mặc định: USER, nếu Admin=true thì thêm ADMIN
            var roles = new ArrayList<GrantedAuthority>();
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
            if (c.getAdmin()) {
                roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            return new org.springframework.security.core.userdetails.User(
                    c.getUsername(), c.getPassword(), roles
            );
        };
    }


    // ===== 3) AuthenticationManager =====
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 4) JWT Filter
    // (đọc token -> set Authentication (trả lời câu hỏi là ai))
    @Bean
    public OncePerRequestFilter jwtFilter(UserDetailsService userService) {
        return new OncePerRequestFilter() {

            @Override
            protected boolean shouldNotFilter(HttpServletRequest request) {
                // Bỏ qua auth endpoints để FE gọi login/register không cần token
                return request.getServletPath().startsWith("/api/auth/");
            }

            @Override
            protected void doFilterInternal(
                    HttpServletRequest request,
                    HttpServletResponse response,
                    FilterChain chain
            ) throws ServletException, IOException {

                String header = request.getHeader("Authorization");

                // Không có Bearer token -> không xử lý
                if (header == null || !header.startsWith("Bearer ")) {
                    chain.doFilter(request, response);
                    return;
                }

                try {
                    String token = header.substring(7).trim();

                    String username = Jwts.parserBuilder()
                            .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
                            .build()
                            .parseClaimsJws(token)
                            .getBody()
                            .getSubject();

                    UserDetails user = userService.loadUserByUsername(username);
                    var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);

                } catch (Exception e) {
                    // Token sai/hết hạn -> coi như chưa login
                    SecurityContextHolder.clearContext();
                }

                chain.doFilter(request, response);
            }
        };
    }

    // ===== 6) Định nghĩa bộ rule security =====
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // 1. Tắt CSRF
                .csrf(csrf -> csrf.disable())

                // 2. Không dùng form login mặc định
                .formLogin(f -> f.disable())

                // 3. Không dùng HTTP Basic
                .httpBasic(b -> b.disable())

                // 4. Không dùng session (JWT)
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 5. Nếu chưa login thì trả 401
                .exceptionHandling(e ->
                        e.authenticationEntryPoint((req, res, ex) ->
                                res.sendError(HttpServletResponse.SC_UNAUTHORIZED)
                        )
                )

                // 6. Phân quyền
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
