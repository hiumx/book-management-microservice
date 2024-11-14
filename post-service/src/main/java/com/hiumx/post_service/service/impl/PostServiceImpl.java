package com.hiumx.post_service.service.impl;

import com.hiumx.post_service.dto.request.PostRequest;
import com.hiumx.post_service.dto.response.PageResponse;
import com.hiumx.post_service.dto.response.PostResponse;
import com.hiumx.post_service.entity.Post;
import com.hiumx.post_service.mapper.PostMapper;
import com.hiumx.post_service.repository.PostRepository;
import com.hiumx.post_service.service.PostService;
import com.hiumx.post_service.util.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PostServiceImpl implements PostService {
    PostRepository postRepository;
    PostMapper postMapper;
    DateTimeFormatter dateTimeFormatter;

    @Override
    public PostResponse createPost(PostRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(request.toString());

        Post post = Post.builder()
                .content(request.getContent())
                .createdDate(Instant.now())
                .updatedDate(Instant.now())
                .userId(authentication.getName())
                .build();

        return postMapper.toPostResponse(postRepository.save(post));
    }

    @Override
    public PageResponse<PostResponse> getMyPosts(int page, int pageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);

        Page<Post> posts = postRepository.findByUserId(authentication.getName(), pageable);

        List<PostResponse> postResponses = posts.getContent().stream().map(post -> {
            PostResponse postResponse = postMapper.toPostResponse(post);
            postResponse.setElapseTime(dateTimeFormatter.format(post.getCreatedDate()));
            return postResponse;
        }).toList();

        return PageResponse.<PostResponse>builder()
                .currentPage(page)
                .pageSize(pageSize)
                .totalPage(posts.getTotalPages())
                .totalElements(posts.getTotalElements())
                .data(postResponses)
                .build();
    }
}
