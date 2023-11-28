package com.findTenant.service;

import com.findTenant.domain.Post;

import java.util.List;

public interface IPostService extends IService<Post, String> {

    List<Post> getAllPosts();

}
