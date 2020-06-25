package com.hyunro.bookmark.web.dto.store;

import com.hyunro.bookmark.domain.store.Store;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StoreResponseDto {

    private Long id;
    private String name;
    private String phone;
    private String address;
    private String type;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public StoreResponseDto(Store entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.phone = entity.getPhone();
        this.address = entity.getAddress();
        this.type = entity.getType();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
