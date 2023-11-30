package com.findTenant.controller;

import com.findTenant.domain.Comment;
import com.findTenant.domain.Post;
import com.findTenant.domain.User;
import com.findTenant.dto.PostDTO;
import com.findTenant.factory.PostFactory;
import com.findTenant.service.impl.CommentService;
import com.findTenant.service.impl.JwtService;
import com.findTenant.service.impl.PostService;
import com.findTenant.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final JwtService jwtService;

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService,
                           UserService userService,
                           CommentService commentService,
                          JwtService jwtService) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
        this.jwtService = jwtService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public Post createPost(@RequestBody Post post) {
        User retrievedAuthor = userService.read(jwtService.getUsername());
        Post generatedPost = PostFactory.createPost(post.getPostMessage(), retrievedAuthor);

        return postService.create(generatedPost);
    }

    @GetMapping("/read/{post_id}")
    public PostDTO readPost(@PathVariable String post_id) {
        Post post = postService.read(post_id);
        List<Comment> comments = commentService.getCommentsByPostId(post_id);

        PostDTO postDTO = new PostDTO();
        postDTO.setPost(post);
        postDTO.setComments(comments);

        return postDTO;
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Post updatePost(@PathVariable String id, @RequestBody Post updateRequest) {
        Post oldPost = postService.read(id);

        String loggedInUser = jwtService.getUsername();
        String author = oldPost.getPostAuthor().getUsername();

        if (author.equals(loggedInUser)) {
            Post updatedPost = new Post.Builder()
                    .copy(oldPost)
                    .setPostMessage(updateRequest.getPostMessage())
                    .build();

            System.out.println(updatedPost);
            return postService.update(updatedPost);
        }
        System.out.println(loggedInUser + " did not write this post");
        return null;
    }

    @GetMapping("/get_posts_of/{username}")
    public List<Post> getPosts(@PathVariable String username) {
        return postService.getPostsByUsername(username);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public boolean deletePost(@PathVariable String id) {
        Post postToBeDeleted = postService.read(id);
        String postAuthor = postToBeDeleted.getPostAuthor().getUsername();
        String loggedInUser = jwtService.getUsername();

        if (postAuthor.equals(loggedInUser)) {
            return postService.delete(id);
        }
        System.out.println("You are not authorized to delete this post");
        return false;
    }

}

