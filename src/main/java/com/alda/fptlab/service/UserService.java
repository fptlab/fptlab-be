package com.alda.fptlab.service;

import com.alda.fptlab.entity.User;
import com.alda.fptlab.exception.*;
import com.alda.fptlab.dto.request.UserDTO;

import java.security.Principal;
import java.util.List;


public interface UserService {
    void signupUser(UserDTO userDTO) throws UserAlreadyExistException;
    void updateUserWithRole(Long userId, Long roleId) throws UserNotFoundException, RoleNotFoundException;
    List<User> fetchUserList();
    void updateUserWithSubscription(Long userId, Long subTypeId) throws UserNotFoundException, SubscriptionTypeNotFoundException, SubscriptionAlreadyActiveException;
    User fetchUserById(Long userId) throws UserNotFoundException;
    User fetchUser(Principal principal) throws UserNotFoundException;
}
