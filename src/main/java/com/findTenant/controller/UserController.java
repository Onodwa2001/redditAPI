package com.findTenant.controller;

import com.findTenant.domain.Post;
import com.findTenant.domain.User;
import com.findTenant.factory.UserFactory;
import com.findTenant.service.impl.JwtService;
import com.findTenant.service.impl.PostService;
import com.findTenant.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PostService postService;
    private final JwtService jwtService;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public UserController(UserService userService, PostService postService, JwtService jwtService) {
        this.userService = userService;
        this.postService = postService;
        this.jwtService = jwtService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        String encodedPassword = encoder.encode(user.getPassword());

        User generatedUser = UserFactory.createUser(user.getUsername(), encodedPassword,
                user.getFirstName(), user.getLastName());
        return userService.create(generatedUser);
    }

    @GetMapping("/get_my_posts")
    @PreAuthorize("hasAuthority('USER')")
    public List<Post> getMyPosts() {
        return postService.getPostsByUsername(jwtService.getUsername());
    }

    @GetMapping("/see_voted_posts")
    @PreAuthorize("hasAuthority('USER')")
    public List<Post> getPostsVoted() {
        return postService.getPostsVotedFor(jwtService.getUsername());
    }

}
