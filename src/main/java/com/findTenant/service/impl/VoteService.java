package com.findTenant.service.impl;

import com.findTenant.domain.Post;
import com.findTenant.domain.User;
import com.findTenant.domain.Vote;
import com.findTenant.repository.PostRepository;
import com.findTenant.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostService postService;

    @Autowired
    private VoteService(VoteRepository voteRepository, PostService postService) {
        this.voteRepository = voteRepository;
        this.postService = postService;
    }

    public Vote create(Vote vote) {
        Post post = vote.getPost();
        List<Vote> existingVotes = getVotesByUsernameAndPostId(vote.getUser().getUsername(), post.getPostId());

        if (existingVotes.isEmpty()) {
            boolean isUpdated = updatePostVote(post, vote);

            if (!isUpdated) {
                System.out.println("There was a problem voting");
                return null;
            }

            return voteRepository.save(vote);
        } else {
            Post updatedPost = null;
            int voteDirection;

            if (vote.getVoteType().equals("up")) {
                voteDirection = existingVotes.get(0).getPost().getUpVotes();
                updatedPost = new Post.Builder()
                        .copy(existingVotes.get(0).getPost())
                        .setUpVotes(voteDirection - 1)
                        .build();
            } else if (vote.getVoteType().equals("down")) {
                voteDirection = existingVotes.get(0).getPost().getDownVotes();
                updatedPost = new Post.Builder()
                        .copy(existingVotes.get(0).getPost())
                        .setDownVotes(voteDirection - 1)
                        .build();
            }

            assert updatedPost != null;
            postService.update(updatedPost);
            delete(existingVotes.get(0).getVoteId());
            return existingVotes.get(0);
        }
    }

    public boolean updatePostVote(Post post, Vote vote) {

        if (vote.getVoteType().equals("up")) {
            return postService.upVote(post) != null; // updated successfully
        }

        if (vote.getVoteType().equals("down")) {
            return postService.downVote(post) != null; // updated successfully
        }

        System.out.println(vote.getVoteType() + " is not a correct vote type");
        return false;
    }

    public boolean delete(String id) {
        if (voteRepository.existsById(id)) {
            voteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Vote> getVotesByUsernameAndPostId(String username, String postId) {
        return voteRepository.getVotesByUser_UsernameAndPost_PostId(username, postId);
    }

}
