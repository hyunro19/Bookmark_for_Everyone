package com.hyunro.bookmark.domain.store;

import com.hyunro.bookmark.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    @Column(nullable = false)
    private String name;

    private String phone;

    @Column(length = 500, nullable = false)
    private String address;
    private String type;

    @Column(columnDefinition = "TEXT")
    private String menu_category;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성 // 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Store(String name, String phone, String address, String type) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.type = type;
    }

    // Entity클래스에서는 절대 Setter 메소드를 만들지 않는다.
    // 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가.

    public void update(String name, String phone, String address, String type) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.type = type;
    }
}
