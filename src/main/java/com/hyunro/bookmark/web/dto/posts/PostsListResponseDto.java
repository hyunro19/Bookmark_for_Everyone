package com.hyunro.bookmark.web.dto.posts;

import com.hyunro.bookmark.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long posts_id;
    private String user_name;
    private String topic;
    private String src_url;
    private String src_title;
    private String src_description;

    // Constructor
    public PostsListResponseDto(Posts entity) {
        this.posts_id = entity.getPosts_id();
        this.user_name = entity.getUser_name();
        this.topic = entity.getTopic();
        this.src_url = entity.getSrc_url();
        this.src_title = entity.getSrc_title();
        this.src_description = entity.getSrc_description();
    }
}
