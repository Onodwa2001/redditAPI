package com.findTenant.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Post implements Serializable {

    @Id
    private String postId;
    private String postMessage;
    private int upVotes, downVotes, numberOfComments;
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    protected Post() {}

    private Post(Builder builder) {
        this.postId = builder.postId;
        this.postMessage = builder.postMessage;
        this.upVotes = builder.upVotes;
        this.downVotes = builder.downVotes;
        this.numberOfComments = builder.numberOfComments;
        this.date = builder.date;
//        this.comments = builder.comments;
        this.user = builder.user;
    }

    public String getPostId() {
        return postId;
    }

    public String getPostMessage() {
        return postMessage;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public LocalDateTime getDate() {
        return date;
    }

//    public List<Comment> getComments() {
//        return comments;
//    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", postMessage='" + postMessage + '\'' +
                ", upVotes=" + upVotes +
                ", downVotes=" + downVotes +
                ", numberOfComments=" + numberOfComments +
                ", date=" + date +
                ", user=" + user +
                '}';
    }

    public static class Builder {

        private String postId, postMessage;
        private int upVotes, downVotes, numberOfComments;
        private LocalDateTime date;
//        private List<Comment> comments;
        private User user;

        public Builder() {}

        public Builder setPostId(String postId) {
            this.postId = postId;
            return this;
        }

        public Builder setPostMessage(String postMessage) {
            this.postMessage = postMessage;
            return this;
        }

        public Builder setUpVotes(int upVotes) {
            this.upVotes = upVotes;
            return this;
        }

        public Builder setDownVotes(int downVotes) {
            this.downVotes = downVotes;
            return this;
        }

        public Builder setNumberOfComments(int numberOfComments) {
            this.numberOfComments = numberOfComments;
            return this;
        }

        public Builder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

//        public Builder setComments(List<Comment> comments) {
//            this.comments = comments;
//            return this;
//        }

        public Builder copy(Post post) {
            this.postId = post.postId;
            this.postMessage = post.postMessage;
            this.upVotes = post.upVotes;
            this.downVotes = post.downVotes;
            this.numberOfComments = post.numberOfComments;
            this.date = post.date;
            this.user = post.user;
//            this.comments = post.comments;
            return this;
        }

        public Post build() {
            return new Post(this);
        }

    }

}
