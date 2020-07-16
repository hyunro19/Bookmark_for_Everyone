package com.hyunro.bookmark.web.dto.user;

import com.hyunro.bookmark.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String password;

    // Constructor
    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
    }
}
