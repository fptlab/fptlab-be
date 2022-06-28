package com.alda.fptlab.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique",
                columnNames = "email"
        )
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Please enter first name")
    private String firstName;
    @NotBlank(message = "Please enter last name")
    private String lastName;
    @NotBlank(message = "Please enter email")
    private String email;
    @NotBlank(message = "Please enter password")
    private String password;
    private boolean enabled = false;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "user_role_map",
            joinColumns = @JoinColumn(
                    name = "user_ide",
                    referencedColumnName = "userId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "roleId"
            )
    )

    private Collection<Role> roles = new ArrayList<>();

}
