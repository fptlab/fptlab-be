package com.alda.fptlab.service.impl;

import com.alda.fptlab.dto.request.PersonalTrainerCalendarDTO;
import com.alda.fptlab.dto.request.PersonalTrainerDTO;
import com.alda.fptlab.entity.PersonalTrainer;
import com.alda.fptlab.entity.PersonalTrainerCalendar;
import com.alda.fptlab.entity.Slot;
import com.alda.fptlab.enums.EWorkOutDuration;
import com.alda.fptlab.exception.PersonalTrainerNotFoundException;
import com.alda.fptlab.repository.PersonalTrainerCalendarRepository;
import com.alda.fptlab.repository.PersonalTrainerRepository;
import com.alda.fptlab.repository.SlotRepository;
import com.alda.fptlab.service.PersonalTrainerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PersonalTrainerServiceImpl implements PersonalTrainerService {

    private final PersonalTrainerRepository personalTrainerRepository;
    private final PersonalTrainerCalendarRepository personalTrainerCalendarRepository;
    private final SlotRepository slotRepository;

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

    @Override
    public List<PersonalTrainer> fetchPersonalTrainerList() {
        log.info("fetchPersonalTrainerList START");
        return personalTrainerRepository.findAll();
    }

    @Override
    public void updatePersonalTrainer(Long personalTrainerId, PersonalTrainerDTO personalTrainerDTO) throws PersonalTrainerNotFoundException, ParseException {
        log.info("updatePersonalTrainer START");
        PersonalTrainer personalTrainer = personalTrainerRepository.findById(personalTrainerId)
                .orElseThrow(() -> new PersonalTrainerNotFoundException("Personal trainer not found"));

        personalTrainer.setFirstName(personalTrainerDTO.getFirstName());
        personalTrainer.setLastName(personalTrainerDTO.getLastName());

        for(PersonalTrainerCalendarDTO personalTrainerCalendarDTO: personalTrainerDTO.getPersonalTrainerCalendarList()) {

            int tf = Integer.parseInt(personalTrainerCalendarDTO.getTimeFrom().replace(":", ""));
            int tt = Integer.parseInt(personalTrainerCalendarDTO.getTimeTo().replace(":", ""));

            PersonalTrainerCalendar personalTrainerCalendar = PersonalTrainerCalendar.builder()
                    .month(personalTrainerCalendarDTO.getMonth())
                    .day(personalTrainerCalendarDTO.getDay())
                    .personalTrainer(personalTrainer)
                    .timeFromLabel(personalTrainerCalendarDTO.getTimeFrom())
                    .timeToLabel(personalTrainerCalendarDTO.getTimeTo())
                    .timeFrom(tf)
                    .timeTo(tt)
                    .build();
            personalTrainerCalendarRepository.save(personalTrainerCalendar);

            String hourBegin = personalTrainerCalendarDTO.getTimeFrom().split(":")[0];
            String minuteBegin = personalTrainerCalendarDTO.getTimeFrom().split(":")[1];

            String hourEnd = personalTrainerCalendarDTO.getTimeTo().split(":")[0];
            String minuteEnd = personalTrainerCalendarDTO.getTimeTo().split(":")[1];

            int begin = (Integer.parseInt(hourBegin) * 60) + Integer.parseInt(minuteBegin);
            int end = (Integer.parseInt(hourEnd) * 60) + Integer.parseInt(minuteEnd);

            for (EWorkOutDuration e: EWorkOutDuration.values()) {
                List<String> slotList = getTimeSlot(begin,end, e.getDuration());
                addSlotToPersonalTrainerCalendar(slotList, personalTrainerCalendar, e);
            }
        }
    }


    public List<String> getTimeSlot(int begin, int end, int duration) {
        List<String> timeList = new ArrayList<>();
        List<String> slotList = new ArrayList<>();
        for (int time = begin; time <= end; time += duration) {
            timeList.add(String.format("%02d:%02d", time / 60, time % 60));
        }

        for (int i = 0; i < timeList.size() -1; i++) {
            slotList.add(timeList.get(i).concat("-").concat(timeList.get(i+1)));
        }

        return slotList;
    }

    public void addSlotToPersonalTrainerCalendar(List<String> slotList, PersonalTrainerCalendar personalTrainerCalendar, EWorkOutDuration workOutDuration) throws ParseException {
        for (String s: slotList) {
            // s = 10:00-10:30
            String tfLabel = s.split("-")[0];
            String ttLabel = s.split("-")[1];

           int tf = Integer.parseInt(tfLabel.replace(":", ""));
           int tt = Integer.parseInt(ttLabel.replace(":", ""));


            Slot slot = Slot.builder()
                    .personalTrainerCalendar(personalTrainerCalendar)
                    .timeFromLabel(tfLabel)
                    .timeToLabel(ttLabel)
                    .timeFrom(tf)
                    .workOutDuration(workOutDuration)
                    .timeTo(tt)
                    .build();
            slotRepository.save(slot);
        }
    }
}


