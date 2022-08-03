package com.alda.fptlab.service;

import com.alda.fptlab.dto.request.PersonalTrainerDTO;
import com.alda.fptlab.entity.PersonalTrainer;
import com.alda.fptlab.exception.PersonalTrainerNotFoundException;

import java.text.ParseException;
import java.util.List;

public interface PersonalTrainerService {
    void savePersonalTrainer(PersonalTrainerDTO personalTrainerDTO);

    List<PersonalTrainer> fetchPersonalTrainerList();

    void updatePersonalTrainer(Long personalTrainerId, PersonalTrainerDTO personalTrainerDTO) throws PersonalTrainerNotFoundException, ParseException;
}
