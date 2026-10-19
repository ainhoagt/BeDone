package com.example.be_done_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @JsonProperty("email")
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;
    @JsonProperty("password")
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
