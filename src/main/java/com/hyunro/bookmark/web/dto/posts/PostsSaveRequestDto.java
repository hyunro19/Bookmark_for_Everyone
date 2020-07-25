package com.hyunro.bookmark.web.dto.posts;

import com.hyunro.bookmark.domain.posts.Posts;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.domain.user.UserRepository;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsSaveRequestDto {

    private Long user_id;
    private String user_name;
    private String topic;
    private String src_url;
    private String src_title;
    private String src_description;


    @Builder
    public PostsSaveRequestDto(Long user_id, String user_name, String topic, String src_url, String src_title, String src_description) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.topic = topic;
        this.src_url = src_url;
        this.src_title = src_title;
        this.src_description = src_description;
    }

    public Posts toEntity(User user) {
        return Posts.builder()
                .user(user)
                .user_name(user_name)
                .topic(topic)
                .src_url(src_url)
                .src_title(src_title)
                .src_description(src_description)
                .build();
    }
}
