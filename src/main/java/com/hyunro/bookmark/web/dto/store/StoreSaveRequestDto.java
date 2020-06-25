package com.hyunro.bookmark.web.dto.store;

import com.hyunro.bookmark.domain.store.Store;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StoreSaveRequestDto {
    private String name;
    private String phone;
    private String address;
    private String type;

    @Builder
    public StoreSaveRequestDto(String name, String phone, String address, String type) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.type = type;
    }

    public Store toEntity() {
        return Store.builder()
                .name(name)
                .phone(phone)
                .address(address)
                .type(type)
                .build();
    }

    // Entity클래스와 유사한 형태임에도 Dto클래스를 추가 생성
    // 절대로 Entity클래스를 Request/Response클래스로 사용해서는 안된다.
    // Entity 클래스는 테이블 생성, 스키마 변경과 직결

    // Dto는 View를 위한 클래스로 자주 변경됨.
}
