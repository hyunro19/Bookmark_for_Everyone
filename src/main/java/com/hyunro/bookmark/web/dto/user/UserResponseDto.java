package com.hyunro.bookmark.web.dto.user;

import com.hyunro.bookmark.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long user_id;
    private final String user_name;
    private final String email;

    @Builder
    public UserResponseDto(User entity) {
        this.user_id = entity.getUser_id();
        this.user_name = entity.getName();
        this.email = entity.getEmail();
    }
}
