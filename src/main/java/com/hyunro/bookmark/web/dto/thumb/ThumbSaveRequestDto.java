package com.hyunro.bookmark.web.dto.thumb;

import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.thumb.Thumb;
import com.hyunro.bookmark.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ThumbSaveRequestDto {

    private Long bookmark_id;

    @Builder
    public ThumbSaveRequestDto(Long bookmark_id) {
        this.bookmark_id = bookmark_id;
    }

    public Thumb toEntity(User user, Bookmark bookmark) {
        return Thumb.builder()
                .user(user)
                .bookmark(bookmark)
                .build();
    }
}
