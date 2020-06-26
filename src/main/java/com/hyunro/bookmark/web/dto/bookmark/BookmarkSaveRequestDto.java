package com.hyunro.bookmark.web.dto.bookmark;

import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookmarkSaveRequestDto {

//    private User user;
//    private String user_name;
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
    public BookmarkSaveRequestDto(//User user, String user_name,
                                  String topic, String url,
                                  String src_title, String src_description, String content,
                                  Integer number_comment, Integer number_thumb, Integer number_share,
                                  Integer is_public) {
//        this.user = user;
//        this.user_name = user_name;
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

    public Bookmark toEntity(User user) {
        return Bookmark.builder()
                .user(user)
//                .user_name(user.getName())
                .topic(topic)
                .url(url)
                .src_title(src_title)
                .src_description(src_description)
                .content(content)
                .number_comment(number_comment)
                .number_thumb(number_thumb)
                .number_share(number_share)
                .is_public(is_public)
                .build();
    }
}
