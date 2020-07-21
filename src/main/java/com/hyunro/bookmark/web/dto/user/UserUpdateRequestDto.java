package com.hyunro.bookmark.web.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private String email;
    private String password_old;
    private String password_new;
    private String name_new;

    @Builder
    public UserUpdateRequestDto(String email, String password_old, String password_new, String name_new) {
        this.email = email;
        this.password_old = password_old;
        this.password_new = password_new;
        this.name_new = name_new;
    }

}
