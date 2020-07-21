package com.hyunro.bookmark.web.dto.user;

import com.hyunro.bookmark.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLoginRequestDto {
    private String email;
    private String password;

    @Builder
    public UserLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}