package com.findTenant.factory;

import com.findTenant.domain.Comment;
import com.findTenant.domain.CommentVote;
import com.findTenant.domain.User;
import com.findTenant.util.Helper;

public class CommentVoteFactory {

    public static CommentVote createCommentVote(Comment comment, User voter, String voteType) {

        if (comment == null) {
            System.out.println("Comment in CommentVote cannot be null");
            return null;
        }

        if (voter == null) {
            System.out.println("Voter in CommentVote cannot be null");
            return null;
        }

        if (Helper.isNullOrEmpty(voteType)) {
            System.out.println("Comment vote type cannot be null");
            return null;
        }

        if (!(voteType.equals("up") || voteType.equals("down"))) {
            System.out.println("Vote type must be either up or down");
            return null;
        }

        String id = Helper.generateId();

        return new CommentVote.Builder()
                .setCommentVoteId(id)
                .setComment(comment)
                .setVoter(voter)
                .setVoteType(voteType)
                .build();

    }

}
