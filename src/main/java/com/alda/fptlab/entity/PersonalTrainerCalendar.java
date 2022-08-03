package com.alda.fptlab.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalTrainerCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int month;
    private int day;

    @ManyToOne
    @JoinColumn(
            name = "personal_trainer_id",
            nullable = false
    )
    @JsonBackReference
    private PersonalTrainer personalTrainer;

    @OneToMany(
            mappedBy = "personalTrainerCalendar"
    )
    @JsonManagedReference
    @Builder.Default
    Collection<Slot> slotList = new ArrayList<>();

    private int timeFrom;
    private int timeTo;
    private String timeFromLabel;
    private String timeToLabel;

}
