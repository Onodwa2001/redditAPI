package com.findTenant.dto;

import com.findTenant.domain.Comment;
import com.findTenant.domain.Post;

import java.util.List;

public class PostDTO {

    private Post post;
    private List<Comment> comments;

    public PostDTO() {}

    public PostDTO(Post post, List<Comment> comments) {
        this.post = post;
        this.comments = comments;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "post=" + post +
                ", comments=" + comments +
                '}';
    }
}
