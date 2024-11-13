package com.hiumx.post_service.controller;

import com.hiumx.post_service.dto.request.PostRequest;
import com.hiumx.post_service.dto.response.ApiResponse;
import com.hiumx.post_service.dto.response.PostResponse;
import com.hiumx.post_service.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @PostMapping("/create")
    public ApiResponse<PostResponse> createPost(@RequestBody PostRequest request) {
        return ApiResponse.<PostResponse>builder()
                .code(1000)
                .message("Create new post successfully")
                .result(postService.createPost(request))
                .build();
    }

    @GetMapping("/my-post")
    public ApiResponse<List<PostResponse>> getMyPosts() {
        return ApiResponse.<List<PostResponse>>builder()
                .code(1000)
                .message("Create new post successfully")
                .result(postService.getMyPosts())
                .build();
    }
}
