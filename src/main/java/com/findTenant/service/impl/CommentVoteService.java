package com.findTenant.service.impl;

import com.findTenant.domain.Comment;
import com.findTenant.domain.CommentVote;
import com.findTenant.domain.Post;
import com.findTenant.domain.Vote;
import com.findTenant.repository.CommentVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentVoteService {

    private final CommentService commentService;
    private final CommentVoteRepository commentVoteRepository;

    @Autowired
    public CommentVoteService(CommentService commentService, CommentVoteRepository commentVoteRepository) {
        this.commentService = commentService;
        this.commentVoteRepository = commentVoteRepository;
    }

    public CommentVote create(CommentVote vote) {
        Comment comment = vote.getComment();
        List<CommentVote> existingVotes = getCommentVotesByUsernameAndCommentId(vote.getVoter().getUsername(), comment.getCommentId());

        if (existingVotes.isEmpty()) {
            boolean isUpdated = updateCommentVote(comment, vote);

            if (!isUpdated) {
                System.out.println("There was a problem voting");
                return null;
            }

            return commentVoteRepository.save(vote);
        } else {
            Comment updatedComment = null;
            int voteDirection;

            if (vote.getVoteType().equals("up")) {
                voteDirection = existingVotes.get(0).getComment().getUpVotes();
                updatedComment = new Comment.Builder()
                        .copy(existingVotes.get(0).getComment())
                        .setUpVotes(voteDirection - 1)
                        .build();
            } else if (vote.getVoteType().equals("down")) {
                voteDirection = existingVotes.get(0).getComment().getDownVotes();
                updatedComment = new Comment.Builder()
                        .copy(existingVotes.get(0).getComment())
                        .setDownVotes(voteDirection - 1)
                        .build();
            }

            assert updatedComment != null;
            commentService.update(updatedComment);
            delete(existingVotes.get(0).getCommentVoteId());
            return existingVotes.get(0);
        }
    }

    public boolean updateCommentVote(Comment comment, CommentVote commentVote) {
        if (commentVote.getVoteType().equals("up")) {
            return commentService.upVote(comment) != null; // updated successfully
        }

        if (commentVote.getVoteType().equals("down")) {
            return commentService.downVote(comment) != null; // updated successfully
        }

        System.out.println(commentVote.getVoteType() + " is not a correct vote type");
        return false;
    }

    public boolean delete(String id) {
        if (commentVoteRepository.existsById(id)) {
            commentVoteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<CommentVote> getCommentVotesByUsernameAndCommentId(String username, String postId) {
        return commentVoteRepository.getCommentVoteByVoter_UsernameAndComment_CommentId(username, postId);
    }

}
