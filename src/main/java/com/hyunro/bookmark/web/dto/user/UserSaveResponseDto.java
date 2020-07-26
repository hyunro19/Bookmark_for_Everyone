package com.hyunro.bookmark.web.dto.user;

import com.hyunro.bookmark.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSaveResponseDto {

    private Long user_id;
    private boolean logged = false;

    // Constructor
    public UserSaveResponseDto() {

    }

    public UserSaveResponseDto(User entity) {
        this.user_id = entity.getUser_id();
        this.logged = true;
    }
}
