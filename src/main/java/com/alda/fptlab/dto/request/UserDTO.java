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
    @NotBlank(message = "Campo nome obbligatorio!")
    private String firstName;

    @NotBlank(message = "Campo cognome obbligatorio!")
    private String lastName;

    @NotBlank(message = "Campo email obbligatorio!")
    @Email(message = "Formato mail non valido!")
    private String email;

    @NotBlank(message = "Campo password obbligatorio!")
    private String password;
}
