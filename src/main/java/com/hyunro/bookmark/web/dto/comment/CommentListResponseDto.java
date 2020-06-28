package com.hyunro.bookmark.web.dto.comment;

import com.hyunro.bookmark.domain.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentListResponseDto {
    private Long id;
    private String user_name;
    private String content;

    // Constructor
    public CommentListResponseDto(Comment entity) {
        this.id = entity.getId();
        this.user_name = entity.getUser_name();
        this.content = entity.getContent();
    }
}
