package com.findTenant.repository;

import com.findTenant.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    List<Post> getPostsByPostAuthor_Username(String username);

}
