package com.alda.fptlab.service.impl;

import com.alda.fptlab.entity.Subscription;
import com.alda.fptlab.entity.SubscriptionType;
import com.alda.fptlab.enums.ERole;
import com.alda.fptlab.entity.Role;
import com.alda.fptlab.entity.User;
import com.alda.fptlab.exception.RoleNotFoundException;
import com.alda.fptlab.exception.SubscriptionTypeNotFoundException;
import com.alda.fptlab.exception.UserAlreadyExistException;
import com.alda.fptlab.exception.UserNotFoundException;
import com.alda.fptlab.dto.request.UserDTO;
import com.alda.fptlab.repository.RoleRepository;
import com.alda.fptlab.repository.SubscriptionRepository;
import com.alda.fptlab.repository.SubscriptionTypeRepository;
import com.alda.fptlab.repository.UserRepository;
import com.alda.fptlab.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public User updateUser(Long userId, Long subTypeId) throws UserNotFoundException, SubscriptionTypeNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            log.error("User not found");
            throw new UserNotFoundException("User not found");
        }
        Optional<SubscriptionType> subscriptionType = subscriptionTypeRepository.findById(subTypeId);
        if(!subscriptionType.isPresent()) {
            log.error("SubscriptionType not found");
            throw new SubscriptionTypeNotFoundException("SubscriptionType not found");
        }

        Subscription subscription = Subscription.builder()
                .user(user.get())
                .subscriptionType(subscriptionType.get())
                .isActive(true)
                .startDate(new Date())
                .reservationLeft(subscriptionType.get().getLessonPackage())
                .build();

        userRepository.save(user.get());
        subscriptionRepository.save(subscription);
        user.get().setEnabled(true);
        return userRepository.save(user.get());
    }
}
