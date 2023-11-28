package com.findTenant.service.impl;

import com.findTenant.domain.Vote;
import com.findTenant.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    private VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote create(Vote vote) {
        return voteRepository.save(vote);
    }

    public boolean delete(String id) {
        if (voteRepository.existsById(id)) {
            voteRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
