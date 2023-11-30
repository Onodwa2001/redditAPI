package com.findTenant.controller;

import com.findTenant.domain.Post;
import com.findTenant.domain.User;
import com.findTenant.domain.Vote;
import com.findTenant.factory.VoteFactory;
import com.findTenant.service.impl.JwtService;
import com.findTenant.service.impl.PostService;
import com.findTenant.service.impl.UserService;
import com.findTenant.service.impl.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;
    private final PostService postService;
    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public VoteController(VoteService voteService, PostService postService,
                          UserService userService, JwtService jwtService) {
        this.voteService = voteService;
        this.postService = postService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/upvote/{post_id}")
    @PreAuthorize("hasAuthority('USER')")
    public Vote upVote(@PathVariable String post_id) {
        System.out.println(postService);
        System.out.println(userService);
        System.out.println(jwtService);
        Post post = postService.read(post_id);
        User voter = userService.read(jwtService.getUsername());

        Vote generatedVote = VoteFactory.createVote(post, voter, "up");
        System.out.println(generatedVote);
        assert generatedVote != null;
        return voteService.create(generatedVote);
    }

    @PostMapping("/downVote/{post_id}")
    @PreAuthorize("hasAuthority('USER')")
    public Vote downVote(@PathVariable String post_id) {
        Post post = postService.read(post_id);
        User voter = userService.read(jwtService.getUsername());
        Vote generatedVote = VoteFactory.createVote(post, voter, "down");
        assert generatedVote != null;
        return voteService.create(generatedVote);
    }

}
