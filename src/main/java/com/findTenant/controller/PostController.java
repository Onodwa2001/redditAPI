package com.findTenant.controller;

import com.findTenant.domain.Comment;
import com.findTenant.domain.Post;
import com.findTenant.domain.User;
import com.findTenant.dto.PostDTO;
import com.findTenant.factory.PostFactory;
import com.findTenant.factory.UserFactory;
import com.findTenant.service.impl.CommentService;
import com.findTenant.service.impl.PostService;
import com.findTenant.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    private PostController(PostService postService,
                           UserService userService,
                           CommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public Post createPost(@RequestBody Post post) {
        User generatedUser = userService.read(post.getUser().getUsername());
        Post generatedPost = PostFactory.createPost(post.getPostMessage(), generatedUser);

        return postService.create(generatedPost);
    }

    @GetMapping("/{post_id}")
    public PostDTO readPost(@PathVariable String post_id) {
        Post post = postService.read(post_id);
        List<Comment> comments = commentService.getCommentsByPostId(post_id);

        PostDTO postDTO = new PostDTO();
        postDTO.setPost(post);
        postDTO.setComments(comments);

        return postDTO;
    }

    @GetMapping("/{username}/see_voted_posts")
    private List<Post> getPostsVoted(@PathVariable String username) {
        return postService.getPostsVotedFor(username);
    }

}

