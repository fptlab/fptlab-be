package com.alda.fptlab.dto.request;

import com.alda.fptlab.entity.PersonalTrainerCalendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalTrainerDTO {
    @NotBlank(message = "Campo nome obbligatorio!")
    private String firstName;

    @NotBlank(message = "Campo cognome obbligatorio!")
    private String lastName;

    @NotBlank
    Collection<PersonalTrainerCalendar> personalTrainerCalendarList;
}
