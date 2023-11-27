package com.findTenant.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Comment implements Serializable {

    @Id
    private String commentId;
    private String commentMessage;
    private LocalDateTime date;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;

    protected Comment() {}

    private Comment(Builder builder) {
        this.commentId = builder.commentId;
        this.commentMessage = builder.commentMessage;
        this.date = builder.date;
        this.post = builder.post;
        this.user = builder.user;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId) && Objects.equals(commentMessage, comment.commentMessage) && Objects.equals(date, comment.date) && Objects.equals(post, comment.post) && Objects.equals(user, comment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, commentMessage, date, post, user);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId='" + commentId + '\'' +
                ", commentMessage='" + commentMessage + '\'' +
                ", date=" + date +
                ", post=" + post +
                ", user=" + user +
                '}';
    }

    public static class Builder {

        private String commentId, commentMessage;
        private LocalDateTime date;
        private Post post;
        private User user;

        public Builder() {}

        public Builder setCommentId(String commentId) {
            this.commentId = commentId;
            return this;
        }

        public Builder setCommentMessage(String commentMessage) {
            this.commentMessage = commentMessage;
            return this;
        }

        public Builder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder setPost(Post post) {
            this.post = post;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder copy(Comment comment) {
            this.commentId = comment.commentId;
            this.commentMessage = comment.commentMessage;
            this.date = comment.date;
            this.post = comment.post;
            this.user = comment.user;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }

    }

}
