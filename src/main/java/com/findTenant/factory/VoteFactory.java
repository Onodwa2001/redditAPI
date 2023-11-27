package com.findTenant.factory;

import com.findTenant.domain.Post;
import com.findTenant.domain.User;
import com.findTenant.domain.Vote;
import com.findTenant.util.Helper;

public class VoteFactory {

    public static Vote createVote(Post post, User user, String voteType) {

        if (post == null) {
            System.out.println("Post in Vote cannot be null");
            return null;
        }

        if (user == null) {
            System.out.println("User in Vote cannot be null");
            return null;
        }

        if (Helper.isNullOrEmpty(voteType)) {
            System.out.println("Vote type cannot be null");
            return null;
        }

        if (!(voteType.equals("up") || voteType.equals("down"))) {
            System.out.println("Vote type must be either up or down");
            return null;
        }

        String id = Helper.generateId();

        return new Vote.Builder()
                .setVoteId(id)
                .setPost(post)
                .setUser(user)
                .build();
    }

}
