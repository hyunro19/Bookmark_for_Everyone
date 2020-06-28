package com.hyunro.bookmark.web.dto.comment;

import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.comment.Comment;
import com.hyunro.bookmark.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentSaveRequestDto {

    private Long bookmark_id;
    private String content;

    @Builder
    public CommentSaveRequestDto(Long bookmark_id, String content) {
        this.bookmark_id = bookmark_id;
        this.content = content;
    }

    public Comment toEntity(User user, Bookmark bookmark) {
        return Comment.builder()
                .user(user)
                .user_name(user.getName())
                .bookmark(bookmark)
                .content(content)
                .build();
    }
}
