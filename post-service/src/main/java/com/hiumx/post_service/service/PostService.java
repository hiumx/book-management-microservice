package com.hiumx.post_service.service;

import com.hiumx.post_service.dto.request.PostRequest;
import com.hiumx.post_service.dto.response.PageResponse;
import com.hiumx.post_service.dto.response.PostResponse;

public interface PostService {
    PostResponse createPost(PostRequest request);
    PageResponse<PostResponse> getMyPosts(int page, int pageSize);
}
