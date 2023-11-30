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
    private User commentAuthor;
    @ManyToOne
    private Post post;
    private int upVotes, downVotes;

    protected Comment() {}

    private Comment(Builder builder) {
        this.commentId = builder.commentId;
        this.commentMessage = builder.commentMessage;
        this.date = builder.date;
        this.commentAuthor = builder.commentAuthor;
        this.post = builder.post;
        this.upVotes = builder.upVotes;
        this.downVotes = builder.downVotes;
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

    public User getCommentAuthor() {
        return commentAuthor;
    }

    public Post getPost() {
        return post;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return upVotes == comment.upVotes && downVotes == comment.downVotes && Objects.equals(commentId, comment.commentId) && Objects.equals(commentMessage, comment.commentMessage) && Objects.equals(date, comment.date) && Objects.equals(commentAuthor, comment.commentAuthor) && Objects.equals(post, comment.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, commentMessage, date, commentAuthor, post, upVotes, downVotes);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId='" + commentId + '\'' +
                ", commentMessage='" + commentMessage + '\'' +
                ", date=" + date +
                ", commentAuthor=" + commentAuthor +
                ", post=" + post +
                ", upVotes=" + upVotes +
                ", downVotes=" + downVotes +
                '}';
    }

    public static class Builder {

        private String commentId, commentMessage;
        private LocalDateTime date;
        private User commentAuthor;
        private Post post;
        private int upVotes, downVotes;

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

        public Builder setCommentAuthor(User commentAuthor) {
            this.commentAuthor = commentAuthor;
            return this;
        }

        public Builder setPost(Post post) {
            this.post = post;
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

        public Builder copy(Comment comment) {
            this.commentId = comment.commentId;
            this.commentMessage = comment.commentMessage;
            this.date = comment.date;
            this.commentAuthor = comment.commentAuthor;
            this.post = comment.post;
            this.upVotes = comment.upVotes;
            this.downVotes = comment.downVotes;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }

    }

}
