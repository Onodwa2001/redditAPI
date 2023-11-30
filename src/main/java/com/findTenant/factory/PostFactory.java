package com.findTenant.factory;

import com.findTenant.domain.Post;
import com.findTenant.domain.User;
import com.findTenant.util.Helper;

import java.time.LocalDateTime;

public class PostFactory {

    public static Post createPost(String postMessage, User postAuthor) {

        if (Helper.isNullOrEmpty(postMessage)) {
            System.out.println("Post cannot be null");
            return null;
        }

        return new Post.Builder()
                .setPostId(Helper.generateId())
                .setPostMessage(postMessage)
                .setUpVotes(0)
                .setDownVotes(0)
                .setNumberOfComments(0)
                .setDate(LocalDateTime.now())
                .setPostAuthor(postAuthor)
                .build();

    }

}
