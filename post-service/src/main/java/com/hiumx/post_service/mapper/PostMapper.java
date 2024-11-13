package com.hiumx.post_service.mapper;

import com.hiumx.post_service.dto.response.PostResponse;
import com.hiumx.post_service.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponse toPostResponse(Post post);
}
