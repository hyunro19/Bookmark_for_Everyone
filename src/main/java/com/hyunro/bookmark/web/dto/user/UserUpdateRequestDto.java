package com.hyunro.bookmark.web.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private String name;
    private String password;

    @Builder
    public UserUpdateRequestDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

}
