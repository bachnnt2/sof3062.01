package org.example.be.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO dùng để thêm mới
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeerRequestDTO {
    private Integer id;

    @NotBlank(message = "Thêm tên đi con zời ơi")
    private String name;

    private String image;

    @NotNull(message = "Say rồi à, nạp tiền đi")
    @DecimalMin(value = "10000", message = "Bèo quá")
    @DecimalMax(value = "100000", message = "Đắt quá quán em không kham được")
    private Double price;
}
