package com.hyunro.bookmark.web.dto.posts;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsUrlRequestDto {
    private String src_url;

    @Builder
    public PostsUrlRequestDto(String src_url) {
        this.src_url = src_url;
    }

}
