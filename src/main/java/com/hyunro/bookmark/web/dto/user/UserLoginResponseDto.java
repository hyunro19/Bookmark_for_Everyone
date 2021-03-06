package com.hyunro.bookmark.web.dto.user;

import com.hyunro.bookmark.domain.user.User;
import lombok.Getter;

@Getter
public class UserLoginResponseDto {

    private Long user_id;
    private boolean logged = false;

    // Constructor
    public UserLoginResponseDto() {

    }

    public UserLoginResponseDto(User entity) {
        this.user_id = entity.getUser_id();
        this.logged = true;
    }
}
