package com.hyunro.bookmark.web.dto.posts;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsUpdateRequestDto {
    private String topic;
    private String src_url;
    private String src_title;
    private String src_description;

    @Builder
    public PostsUpdateRequestDto(String topic, String src_url, String src_title, String src_description) {
        this.topic = topic;;
        this.src_url = src_url;
        this.src_title = src_title;
        this.src_description = src_description;
    }

}
