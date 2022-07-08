package com.alda.fptlab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private User user;
    @ManyToOne
    @JoinColumn(
            name = "subscriptionType_id",
            nullable = false
    )
    private SubscriptionType subscriptionType;
    @OneToMany(mappedBy = "subscription")
    private List<Reservation> reservationList;
}
