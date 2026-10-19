package com.example.be_done_api.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRequestUser {
    @JsonProperty("name")
    @NotBlank(message = "User name cannot be blank")
    private String name;
    @JsonProperty("email")
    @NotBlank(message = "User email cannot be blank")
    private String email;
    @JsonProperty("password")
    @NotBlank(message = "User password cannot be blank")
    private String password;
}
