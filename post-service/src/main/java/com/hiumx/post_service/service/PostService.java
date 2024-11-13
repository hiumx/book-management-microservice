package com.hiumx.post_service.service;

import com.hiumx.post_service.dto.request.PostRequest;
import com.hiumx.post_service.dto.response.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse createPost(PostRequest request);
    List<PostResponse> getMyPosts();
}
