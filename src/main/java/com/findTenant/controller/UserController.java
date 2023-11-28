package com.findTenant.controller;

import com.findTenant.domain.User;
import com.findTenant.factory.UserFactory;
import com.findTenant.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        User generatedUser = UserFactory.createUser(user.getUsername(), user.getPassword(),
                user.getFirstName(), user.getLastName());
        return userService.create(generatedUser);
    }

}
