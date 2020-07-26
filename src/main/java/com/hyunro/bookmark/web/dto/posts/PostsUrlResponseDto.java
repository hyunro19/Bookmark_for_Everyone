package com.hyunro.bookmark.web.dto.posts;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsUrlResponseDto {
    private String src_title;
    private String src_description;
    private String src_img;

    @Builder
    public PostsUrlResponseDto(String src_title, String src_description, String src_img) {
        this.src_title = src_title;
        this.src_description = src_description;
        this.src_img = src_img;
    }

}
