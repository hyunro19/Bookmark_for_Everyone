package com.hyunro.bookmark.web.dto.user;

import com.hyunro.bookmark.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSaveRequestDto {
    private String name;
    private String email;
    private String password;

    @Builder
    public UserSaveRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
