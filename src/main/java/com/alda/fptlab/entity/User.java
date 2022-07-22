package com.alda.fptlab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter first name")
    private String firstName;

    @NotBlank(message = "Please enter last name")
    private String lastName;

    @NotBlank(message = "Please enter email")
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank(message = "Please enter password")
    @JsonIgnore
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
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )
    )
    @Builder.Default
    private Collection<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    @JsonManagedReference
    private Collection<Subscription> subscriptionList = new ArrayList<>();
}
