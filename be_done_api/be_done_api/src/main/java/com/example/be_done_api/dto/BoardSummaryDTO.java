package com.example.be_done_api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardSummaryDTO {
    private Integer id;
    private String name;
    private String colour;
    private String backgroundImage;
}
