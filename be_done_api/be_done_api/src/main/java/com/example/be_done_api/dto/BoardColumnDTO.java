package com.example.be_done_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardColumnDTO {
    private Integer id;
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 30)
    private String name;
    private int positionIndex;
    private List<TaskDTO> tasks;
    @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "Color hexadecimal, p. ej. #FF5733")
    private String colour;
}