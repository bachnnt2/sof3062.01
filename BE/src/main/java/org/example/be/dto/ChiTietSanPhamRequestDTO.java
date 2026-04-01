package org.example.be.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietSanPhamRequestDTO {
    @NotBlank(message = "Không được thiếu màu sắc")
    private String mauSac;

    @NotBlank(message = "Không được thiếu kích cỡ")
    private String kickCo;

    @NotBlank(message = "Không được thiếu chất liệu")
    private String chatLieu;

    @NotNull(message = "Không được thiếu bảo hành")
    private Integer baoHanh;

    @NotBlank(message = "Không được thiếu xuất xứ")
    private String xuatXu;

    @NotNull(message = "Không được thiếu id sản phẩm - khoá ngoại")
    private Integer sanphamid;
}
