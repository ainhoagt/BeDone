package com.example.be_done_api.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {
    @JsonProperty("user_id")
    @NotNull(message = "User ID cannot be blank")
    private Integer id;
    @JsonProperty("name")
    @NotBlank(message = "User name cannot be blank")
    private String name;
    @JsonProperty("email")
    @NotBlank(message = "User email cannot be blank")
    private String email;
    @JsonProperty("password_hash")
    @NotBlank(message = "User password cannot be blank")
    private String password;
}
