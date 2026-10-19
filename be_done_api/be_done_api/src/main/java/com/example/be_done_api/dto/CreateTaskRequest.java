package com.example.be_done_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTaskRequest {
    @JsonProperty("name")
    @NotBlank(message = "Task name cannot be blank")
    private String name;
    @JsonProperty("description")
    @NotBlank(message = "Task description cannot be blank")
    private String description;
    @JsonProperty("colour")
    @NotBlank(message = "Task colour cannot be blank")
    private String colour;
}
