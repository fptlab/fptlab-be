package com.alda.fptlab.service;

import com.alda.fptlab.entity.User;
import com.alda.fptlab.exception.RoleNotFoundException;
import com.alda.fptlab.exception.SubscriptionTypeNotFoundException;
import com.alda.fptlab.exception.UserAlreadyExistException;
import com.alda.fptlab.exception.UserNotFoundException;
import com.alda.fptlab.dto.request.UserDTO;

import java.util.List;


public interface UserService {

    void signupUser(UserDTO userDTO) throws UserAlreadyExistException;
    void updateUserWithRole(Long userId, Long roleId) throws UserNotFoundException, RoleNotFoundException;
    List<User> fetchUserList();
    User updateUser(Long userId, Long subTypeId) throws UserNotFoundException, SubscriptionTypeNotFoundException;
}
