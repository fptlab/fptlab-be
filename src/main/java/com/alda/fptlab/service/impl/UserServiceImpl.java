package com.alda.fptlab.service.impl;

import com.alda.fptlab.entity.Subscription;
import com.alda.fptlab.entity.SubscriptionType;
import com.alda.fptlab.enums.ERole;
import com.alda.fptlab.entity.Role;
import com.alda.fptlab.entity.User;
import com.alda.fptlab.exception.*;
import com.alda.fptlab.dto.request.UserDTO;
import com.alda.fptlab.repository.RoleRepository;
import com.alda.fptlab.repository.SubscriptionRepository;
import com.alda.fptlab.repository.SubscriptionTypeRepository;
import com.alda.fptlab.repository.UserRepository;
import com.alda.fptlab.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signupUser(UserDTO userDTO) throws UserAlreadyExistException {
        log.info("Signup user start");
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()) throw new UserAlreadyExistException("Email già in uso!");
        var user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roles(List.of(roleRepository.findByName(ERole.ROLE_USER)))
                .build();

        userRepository.save(user);
        log.info("Signup user success!");
    }

    @Override
    public void updateUserWithRole(Long userId, Long roleId) throws UserNotFoundException, RoleNotFoundException {
        log.info("Start updateUserWithRole");
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            log.error("User not found");
            throw new UserNotFoundException("User not found");
        }
        Optional<Role> role = roleRepository.   findById(roleId);
        if(!role.isPresent()) {
            log.error("Role not found");
            throw new RoleNotFoundException("Role not found");
        }
        user.get().getRoles().add(role.get());
        log.info("Updating user {} with role {}", user.get().getEmail(), role.get().getName());
        userRepository.save(user.get());
    }

    @Override
    public List<User> fetchUserList() {
        //TODO -> pagination
        log.info("Fetching all the users");
        return userRepository.findAll();
    }

    @Override
    public void updateUserWithSubscription(Long userId, Long subTypeId) throws UserNotFoundException, SubscriptionTypeNotFoundException, SubscriptionAlreadyActiveException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(subTypeId)
                .orElseThrow(() -> new SubscriptionTypeNotFoundException("SubscriptionType not found"));

        List<Subscription> subscriptionList = user.getSubscriptionList()
                .stream()
                .filter(sub -> sub.isActive()).collect(Collectors.toList());

        if(subscriptionList.isEmpty()) {
            Subscription subscription = Subscription.builder()
                    .user(user)
                    .subscriptionType(subscriptionType)
                    .isActive(true)
                    .startDate(new Date())
                    .reservationLeft(subscriptionType.getLessonPackage())
                    .build();
            subscriptionRepository.save(subscription);
            user.setEnabled(true);
        } else {
            throw new SubscriptionAlreadyActiveException("The user already has an active subscription");
        }

        userRepository.save(user);
    }

    @Override
    public User fetchUserById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User fetchUser(Principal principal) throws UserNotFoundException {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
