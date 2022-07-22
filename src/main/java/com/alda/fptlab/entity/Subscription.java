package com.alda.fptlab.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startDate;
    private boolean isActive;
    private int reservationLeft;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    @JsonBackReference
    private User user;

    @OneToOne
    private SubscriptionType subscriptionType;

    @OneToMany(mappedBy = "subscription")
    private List<Reservation> reservationList;
}
