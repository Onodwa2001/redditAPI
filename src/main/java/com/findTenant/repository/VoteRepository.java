package com.findTenant.repository;

import com.findTenant.domain.User;
import com.findTenant.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, String> {

    List<Vote> getVotesByUser_UsernameAndPost_PostId(String username, String postId);

    List<Vote> getVotesByUser_Username(String username);

}
