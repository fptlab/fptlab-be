package com.alda.fptlab.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank
    private String firstName;

    @NotBlank(message = "Cognome obbligatorio!")
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
