package com.alda.fptlab.entity;

import com.alda.fptlab.enums.EWorkOutDuration;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "personal_trainer_calendar_id",
            nullable = false
    )
    @JsonBackReference
    private PersonalTrainerCalendar personalTrainerCalendar;

    @Builder.Default
    private boolean available = true;

    @Enumerated(EnumType.STRING)
    private EWorkOutDuration workOutDuration;

    private String timeFromLabel;
    private String timeToLabel;

    private int timeFrom;
    private int timeTo;
}
