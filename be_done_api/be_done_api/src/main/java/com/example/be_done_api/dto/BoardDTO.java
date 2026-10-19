package com.example.be_done_api.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class BoardDTO {
    private Integer id;                    // sin validación: al crear no existe

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 30)
    private String name;

    @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "Color hexadecimal, p. ej. #FF5733")
    private String colour;

    @Size(max = 100)
    private String backgroundImage;

    private List<BoardColumnDTO> columns;  // ver aviso abajo
}