package com.alda.fptlab.service.impl;

import com.alda.fptlab.entity.Subscription;
import com.alda.fptlab.repository.SubscriptionRepository;
import com.alda.fptlab.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public List<Subscription> fetchSubscriptionList() {
        return subscriptionRepository.findAll();
    }
}
