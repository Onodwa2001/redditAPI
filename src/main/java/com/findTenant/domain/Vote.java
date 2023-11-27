package com.findTenant.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Vote implements Serializable {

    @Id
    private String voteId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private String voteType;

    protected Vote() {}

    private Vote(Builder builder) {
        this.voteId = builder.voteId;
        this.post = builder.post;
        this.user = builder.user;
        this.voteType = builder.voteType;
    }

    public String getVoteId() {
        return voteId;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public String getVoteType() {
        return voteType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(voteId, vote.voteId) && Objects.equals(post, vote.post) && Objects.equals(user, vote.user) && Objects.equals(voteType, vote.voteType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteId, post, user, voteType);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "voteId='" + voteId + '\'' +
                ", post=" + post +
                ", user=" + user +
                ", voteType='" + voteType + '\'' +
                '}';
    }

    public static class Builder {

        private String voteId;
        private Post post;
        private User user;
        private String voteType;

        public Builder() {}

        public Builder setVoteId(String voteId) {
            this.voteId = voteId;
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

        public Builder setVoteType(String voteType) {
            this.voteType = voteType;
            return this;
        }

        public Builder copy(Vote vote) {
            this.voteId = vote.voteId;
            this.post = vote.post;
            this.user = vote.user;
            this.voteType = vote.voteType;
            return this;
        }

        public Vote build() {
            return new Vote(this);
        }

    }

}
