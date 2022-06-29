package com.alda.fptlab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Builder.Default
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

    @Builder.Default
    private Collection<Role> roles = new ArrayList<>();
}
