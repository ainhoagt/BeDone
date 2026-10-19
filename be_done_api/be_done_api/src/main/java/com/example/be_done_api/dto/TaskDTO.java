package com.example.be_done_api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private Integer id;
    private String name;
    private String description;
    private String colour;
    private int positionIndex;
}
