package com.hyunro.bookmark.web.dto.store;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StoreUpdateRequestDto {
    private String name;
    private String phone;
    private String address;
    private String type;

    @Builder
    public StoreUpdateRequestDto(String name, String phone, String address, String type) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.type = type;
    }

}
