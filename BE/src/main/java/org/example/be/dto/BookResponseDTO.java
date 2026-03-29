package org.example.be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {
    private Integer idSach;
    private String tieuDe;
    private Integer idTacGia;
    private String tenTacGia;
}
