package com.alda.fptlab.service.impl;

import com.alda.fptlab.entity.Role;
import com.alda.fptlab.entity.User;
import com.alda.fptlab.error.RoleNotFoundException;
import com.alda.fptlab.error.UserNotFoundException;
import com.alda.fptlab.dto.UserDTO;
import com.alda.fptlab.repository.RoleRepository;
import com.alda.fptlab.repository.UserRepository;
import com.alda.fptlab.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userEmail);
        if(user == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        log.info("User found: {}", userEmail);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        log.info("Saving new user");
        var user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roles(List.of(roleRepository.findByName("USER")))
                .build();

        userRepository.save(user);
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
}
