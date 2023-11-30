package com.findTenant.repository;

import com.findTenant.domain.CommentVote;
import com.findTenant.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentVoteRepository extends JpaRepository<CommentVote, String> {

    List<CommentVote> getCommentVoteByVoter_UsernameAndComment_CommentId(String username, String postId);

    List<CommentVote> getVotesByVoter_Username(String username);

}
