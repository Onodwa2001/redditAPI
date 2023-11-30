package com.findTenant.factory;

import com.findTenant.domain.Comment;
import com.findTenant.domain.Post;
import com.findTenant.domain.User;
import com.findTenant.util.Helper;

import java.time.LocalDateTime;

public class CommentFactory {

    public static Comment createComment(String commendMessage, Post post, User commentAuthor) {

        if (Helper.isNullOrEmpty(commendMessage)) {
            System.out.println("Comment cannot be null");
            return null;
        }

        if (post == null) {
            System.out.println("Comment post cannot be null");
            return null;
        }

        if (commentAuthor == null) {
            System.out.println("Comment Author cannot be null");
            return null;
        }

        String id = Helper.generateId();

        return new Comment.Builder()
                .setCommentId(id)
                .setCommentMessage(commendMessage)
                .setDate(LocalDateTime.now())
                .setPost(post)
                .setCommentAuthor(commentAuthor)
                .setUpVotes(0)
                .setDownVotes(0)
                .build();

    }

}
