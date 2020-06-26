package com.hyunro.bookmark.web.dto.bookmark;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BookmarkUpdateRequestDto {
    private String topic;
    private String url;
    private String src_title;
    private String src_description;
    private String content;
    private Integer number_comment;
    private Integer number_thumb;
    private Integer number_share;
    private Integer is_public;

    @Builder
    public BookmarkUpdateRequestDto(String topic, String url, String src_title, String src_description,
                                    String content, Integer number_comment, Integer number_thumb, Integer number_share, Integer is_public) {
        this.topic = topic;
        this.url = url;
        this.src_title = src_title;
        this.src_description = src_description;
        this.content = content;
        this.number_comment = number_comment;
        this.number_thumb = number_thumb;
        this.number_share = number_share;
        this.is_public = is_public;
    }

}
