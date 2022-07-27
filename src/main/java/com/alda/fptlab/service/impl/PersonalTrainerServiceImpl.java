package com.alda.fptlab.service.impl;

import com.alda.fptlab.dto.request.PersonalTrainerDTO;
import com.alda.fptlab.entity.PersonalTrainer;
import com.alda.fptlab.repository.PersonalTrainerRepository;
import com.alda.fptlab.service.PersonalTrainerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PersonalTrainerServiceImpl implements PersonalTrainerService {

    private final PersonalTrainerRepository personalTrainerRepository;

    @Override
    public void savePersonalTrainer(PersonalTrainerDTO personalTrainerDTO) {
        log.info("savePersonalTrainer START");

        var personalTrainer = PersonalTrainer.builder()
                .firstName(personalTrainerDTO.getFirstName())
                .lastName(personalTrainerDTO.getLastName())
                .build();

        personalTrainerRepository.save(personalTrainer);
        log.info("savePersonalTrainer success!");
    }
}
