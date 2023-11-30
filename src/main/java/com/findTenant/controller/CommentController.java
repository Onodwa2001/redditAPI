package com.findTenant.controller;

import com.findTenant.domain.Comment;
import com.findTenant.domain.Post;
import com.findTenant.domain.User;
import com.findTenant.factory.CommentFactory;
import com.findTenant.service.impl.CommentService;
import com.findTenant.service.impl.JwtService;
import com.findTenant.service.impl.PostService;
import com.findTenant.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public CommentController(final CommentService commentService,
                              final PostService postService,
                              final UserService userService,
                             final JwtService jwtService) {
        this.commentService = commentService;
        this.postService = postService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public Comment createComment(@RequestBody Comment comment) {
        Post post = postService.read(comment.getPost().getPostId());
        User commenter = userService.read(jwtService.getUsername());

        Comment generatedComment = CommentFactory.createComment(comment.getCommentMessage(),
                post, commenter);
        return commentService.create(generatedComment);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteComment(@PathVariable String id) {
        return commentService.delete(id);
    }

}
