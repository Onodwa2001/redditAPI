package com.findTenant.service.impl;

import com.findTenant.domain.Post;
import com.findTenant.domain.Vote;
import com.findTenant.factory.VoteFactory;
import com.findTenant.repository.PostRepository;
import com.findTenant.repository.VoteRepository;
import com.findTenant.service.IPostService;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final VoteRepository voteRepository;

    private PostService(PostRepository postRepository, VoteRepository voteRepository) {
        this.postRepository = postRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post read(String id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post update(Post post) {
        if (postRepository.existsById(post.getPostId()))
            return postRepository.save(post);
        return null;
    }

    @Override
    public boolean delete(String id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post upVote(Post post) {

        Post updatedPost = new Post.Builder()
                .copy(post)
                .setUpVotes(post.getUpVotes() + 1)
                .build();
        return update(updatedPost);
    }

    public Post downVote(Post post) {
        Post updatedPost = new Post.Builder()
                .copy(post)
                .setDownVotes(post.getDownVotes() + 1)
                .build();
        return update(updatedPost);
    }

    public List<Post> getPostsVotedFor(String username) {
        List<Post> posts = new ArrayList<>();
        List<Vote> votes = voteRepository.getVotesByUser_Username(username);

        for (Vote vote : votes) {
            posts.add(vote.getPost());
        }

        return posts;
    }

}
