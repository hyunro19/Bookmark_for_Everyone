package com.hyunro.bookmark.web.dto.user;

import com.hyunro.bookmark.domain.user.User;
import lombok.Getter;

@Getter
public class UserLoginResponseDto {

    private Long user_id;
    private String name;
    private String email;
    private boolean logged = false;

    // Constructor
    public UserLoginResponseDto() {

    }

    public UserLoginResponseDto(User entity) {
        this.user_id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.logged = true;
    }
}
