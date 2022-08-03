package com.alda.fptlab.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalTrainerCalendarDTO {
    private int month;
    private int day;
    private String timeFrom;
    private String timeTo;
}
