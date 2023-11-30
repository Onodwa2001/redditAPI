package com.findTenant.controller;

import com.findTenant.domain.*;
import com.findTenant.factory.CommentVoteFactory;
import com.findTenant.factory.VoteFactory;
import com.findTenant.service.impl.CommentService;
import com.findTenant.service.impl.CommentVoteService;
import com.findTenant.service.impl.JwtService;
import com.findTenant.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment_vote")
public class CommentVoteController {

    private CommentVoteService commentVoteService;
    private CommentService commentService;
    private UserService voterService;
    private JwtService jwtService;

    @Autowired
    public CommentVoteController(CommentVoteService commentVoteService,
                                 CommentService commentService, UserService voterService,
                                 JwtService jwtService) {
        this.commentVoteService = commentVoteService;
        this.commentService = commentService;
        this.voterService = voterService;
        this.jwtService = jwtService;
    }

    @PostMapping("/upvote/{comment_id}")
    @PreAuthorize("hasAuthority('USER')")
    public CommentVote upVote(@PathVariable String comment_id) {
        Comment comment = commentService.read(comment_id);
        User voter = voterService.read(jwtService.getUsername());

        CommentVote generatedVote = CommentVoteFactory.createCommentVote(comment, voter, "up");
        System.out.println(generatedVote);
        assert generatedVote != null;
        return commentVoteService.create(generatedVote);
    }

    @PostMapping("/downVote/{comment_id}")
    @PreAuthorize("hasAuthority('USER')")
    public CommentVote downVote(@PathVariable String comment_id) {
        Comment comment = commentService.read(comment_id);
        User voter = voterService.read(jwtService.getUsername());
        CommentVote generatedVote = CommentVoteFactory.createCommentVote(comment, voter, "down");
        assert generatedVote != null;
        return commentVoteService.create(generatedVote);
    }

}
