package com.alda.fptlab.service;

import com.alda.fptlab.entity.User;
import com.alda.fptlab.error.RoleNotFoundException;
import com.alda.fptlab.error.UserNotFoundException;
import com.alda.fptlab.model.UserModel;

import java.util.List;


public interface UserService {

    void saveUser(UserModel userModel);
    void updateUserWithRole(Long userId, Long roleId) throws UserNotFoundException, RoleNotFoundException;
    List<User> getUserList();

}
