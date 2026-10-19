package com.example.be_done_api.dto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private List<BoardSummaryDTO> boards;
    private LocalDateTime createdAt;
}
