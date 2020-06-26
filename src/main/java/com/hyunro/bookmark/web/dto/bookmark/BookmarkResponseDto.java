package com.hyunro.bookmark.web.dto.bookmark;

import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookmarkResponseDto {
    private Long id;
    private User user;
    private String user_name;
    private String topic;
    private String url;
    private String src_title;
    private String src_description;
    private String content;
    private Integer number_comment;
    private Integer number_thumb;
    private Integer number_share;
    private Integer is_public;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    // Constructor
    public BookmarkResponseDto(Bookmark entity) {
        this.id = entity.getId();
        this.user = entity.getUser();
        this.user_name = entity.getUser_name();
        this.topic = entity.getTopic();
        this.url = entity.getUrl();
        this.src_title = entity.getSrc_title();
        this.src_description = entity.getSrc_description();
        this.content = entity.getContent();
        this.number_comment = entity.getNumber_comment();
        this.number_thumb = entity.getNumber_thumb();
        this.number_share = entity.getNumber_share();
        this.is_public = entity.getIs_public();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
