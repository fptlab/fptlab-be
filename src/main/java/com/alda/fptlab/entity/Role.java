package com.alda.fptlab.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(
        name = "roles",
        uniqueConstraints = @UniqueConstraint(
                name = "name_unique",
                columnNames = "name"
        )
)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String name;

}
