package com.alda.fptlab.service.impl;

import com.alda.fptlab.entity.SubscriptionType;
import com.alda.fptlab.repository.SubscriptionTypeRepository;
import com.alda.fptlab.service.SubscriptionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    @Override
    public List<SubscriptionType> fetchSubscriptionTypeList() {
        return subscriptionTypeRepository.findAll();
    }
}
