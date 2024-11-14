package com.hiumx.post_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private int currentPage;
    private int totalPage;
    private int pageSize;
    private long totalElements;

    @Builder.Default
    private List<T> data = Collections.emptyList();
}
