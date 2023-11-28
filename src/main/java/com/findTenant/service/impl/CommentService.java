package com.findTenant.service.impl;

import com.findTenant.domain.Comment;
import com.findTenant.repository.CommentRepository;
import com.findTenant.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements IService<Comment, String> {

    private final CommentRepository commentRepository;

    @Autowired
    private CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment read(String id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment update(Comment comment) {
        if (commentRepository.existsById(comment.getCommentId()))
            return commentRepository.save(comment);
        return null;
    }

    @Override
    public boolean delete(String id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Comment> getCommentsByPostId(String postId) {
        return commentRepository.getCommentsByPost_PostId(postId);
    }

}
