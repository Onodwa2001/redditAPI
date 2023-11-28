package com.findTenant.controller;

import com.findTenant.domain.Post;
import com.findTenant.domain.User;
import com.findTenant.domain.Vote;
import com.findTenant.factory.VoteFactory;
import com.findTenant.service.impl.PostService;
import com.findTenant.service.impl.UserService;
import com.findTenant.service.impl.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    private VoteController(VoteService voteService, PostService postService, UserService userService) {
        this.voteService = voteService;
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/upvote/{post_id}/{username}")
    private Vote upVote(@PathVariable String post_id, @PathVariable String username) {
        Post post = postService.read(post_id);
        User voter = userService.read(username);

        Vote generatedVote = VoteFactory.createVote(post, voter, "up");
        System.out.println(generatedVote);
        assert generatedVote != null;
        return voteService.create(generatedVote);
    }

    @PostMapping("/downVote/{post_id}/{username}")
    private Vote downVote(@PathVariable String post_id, @PathVariable String username) {
        Post post = postService.read(post_id);
        User voter = userService.read(username);
        Vote generatedVote = VoteFactory.createVote(post, voter, "down");
        assert generatedVote != null;
        return voteService.create(generatedVote);
    }

}
