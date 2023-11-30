package com.findTenant.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "comment_vote")
public class CommentVote implements Serializable {

    @Id
    private String commentVoteId;
    @ManyToOne
    private User voter;
    @ManyToOne
    private Comment comment;
    private String voteType;


    protected CommentVote() {}

    private CommentVote(Builder builder) {
        this.commentVoteId = builder.commentVoteId;
        this.voter = builder.voter;
        this.comment = builder.comment;
        this.voteType = builder.voteType;
    }

    public String getCommentVoteId() {
        return commentVoteId;
    }

    public User getVoter() {
        return voter;
    }

    public Comment getComment() {
        return comment;
    }

    public String getVoteType() {
        return voteType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentVote that = (CommentVote) o;
        return Objects.equals(commentVoteId, that.commentVoteId) && Objects.equals(voter, that.voter) && Objects.equals(comment, that.comment) && Objects.equals(voteType, that.voteType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentVoteId, voter, comment, voteType);
    }

    @Override
    public String toString() {
        return "CommentVote{" +
                "commentVoteId='" + commentVoteId + '\'' +
                ", voter=" + voter +
                ", comment=" + comment +
                ", voteType='" + voteType + '\'' +
                '}';
    }

    public static class Builder {

        private String commentVoteId;
        private User voter;
        private Comment comment;
        private String voteType;

        public Builder() {}

        public Builder setCommentVoteId(String commentVoteId) {
            this.commentVoteId = commentVoteId;
            return this;
        }

        public Builder setVoter(User voter) {
            this.voter = voter;
            return this;
        }

        public Builder setComment(Comment comment) {
            this.comment = comment;
            return this;
        }

        public Builder setVoteType(String voteType) {
            this.voteType = voteType;
            return this;
        }

        public Builder copy(CommentVote commentVote) {
            this.commentVoteId = commentVote.commentVoteId;
            this.voter = commentVote.voter;
            this.comment = commentVote.comment;
            this.voteType = commentVote.voteType;
            return this;
        }

        public CommentVote build() {
            return new CommentVote(this);
        }

    }

}
