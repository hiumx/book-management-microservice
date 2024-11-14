package com.hiumx.post_service.controller;

import com.hiumx.post_service.dto.request.PostRequest;
import com.hiumx.post_service.dto.response.ApiResponse;
import com.hiumx.post_service.dto.response.PageResponse;
import com.hiumx.post_service.dto.response.PostResponse;
import com.hiumx.post_service.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse<PageResponse<PostResponse>> getMyPosts(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize
    ) {
        return ApiResponse.<PageResponse<PostResponse>>builder()
                .code(1000)
                .message("Create new post successfully")
                .result(postService.getMyPosts(page, pageSize))
                .build();
    }
}
