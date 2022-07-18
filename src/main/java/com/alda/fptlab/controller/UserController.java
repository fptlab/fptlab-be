package com.alda.fptlab.controller;


import com.alda.fptlab.entity.User;
import com.alda.fptlab.exception.RoleNotFoundException;
import com.alda.fptlab.exception.UserNotFoundException;
import com.alda.fptlab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    ResponseEntity<List<User>> fetchUserList() {
        return ResponseEntity.ok().body(userService.fetchUserList());
    }

    @PutMapping("/{id}/roles/{roleId}")
    public ResponseEntity<?> updateUserWithRole(@PathVariable("id") Long userId, @PathVariable("roleId") Long roleId) throws UserNotFoundException, RoleNotFoundException {
        userService.updateUserWithRole(userId, roleId);
        return ResponseEntity.ok().build();
    }

}
