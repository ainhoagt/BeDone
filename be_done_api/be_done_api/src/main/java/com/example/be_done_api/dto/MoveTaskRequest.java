package com.example.be_done_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoveTaskRequest {
    @JsonProperty("target_column_id")
    @NotNull(message = "Target column ID cannot be null")
    private Integer targetColumnId;
    @JsonProperty("target_position")
    @NotNull(message = "Target position cannot be null")
    @PositiveOrZero(message = "Target position must be a non-negative integer")
    private Integer targetPosition;
}