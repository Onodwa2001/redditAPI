package com.findTenant.factory;

import com.findTenant.domain.Post;
import com.findTenant.util.Helper;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostFactory {

    public static Post createPost(String postMessage, int upVotes,
                                  int downVotes, int numberOfComments, LocalDateTime date) {

        LocalDateTime today = LocalDateTime.now();

        if (Helper.isNullOrEmpty(postMessage)) {
            System.out.println("Post cannot be null");
            return null;
        }

        if (numberOfComments < 0) {
            System.out.println("Number of comments cannot be less than zero");
            return null;
        }

        if (date == null) {
            System.out.println("Post date cannot be null");
            return null;
        }

//        if (date.isBefore(today)) {
//            System.out.println("This date has passed");
//            return null;
//        }

        return new Post.Builder()
                .setPostId(Helper.generateId())
                .setUpVotes(upVotes)
                .setDownVotes(downVotes)
                .setNumberOfComments(numberOfComments)
                .setDate(date)
                .build();

    }

}
