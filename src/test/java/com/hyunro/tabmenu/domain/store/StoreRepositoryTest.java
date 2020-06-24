package com.hyunro.tabmenu.domain.store;

import com.hyunro.tabmenu.domain.posts.Posts;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @After // Junit에서 단위테스트가 끝날때마다 수행되는 메소드를 지정
    public void cleanup() {
        storeRepository.deleteAll();
    }

    @Test
    public void 가게저장_불러오기() {
        //given
        String name = "가게이름";
        String phone = "010-7979-5252";
        String address = "서울특별시 중구 다산로 12가길";
        String type = "치킨";

        // 테이블 posts에 insert/update 쿼리를 실행 id가 있다면 update, 없다면 insert
        storeRepository.save(Store.builder()
                .name(name)
                .phone(phone)
                .address(address)
                .type(type)
                .build());

        //when
        List<Store> storeList = storeRepository.findAll(); // 테이블 posts의 모든 데이터를 조회

        //then
        Store store = storeList.get(0);
        assertThat(store.getName()).isEqualTo(name);
        assertThat(store.getPhone()).isEqualTo(phone);
        assertThat(store.getAddress()).isEqualTo(address);
        assertThat(store.getType()).isEqualTo(type);

    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2020,5, 6, 0, 0, 0);
        storeRepository.save(Store.builder()
                .name("name")
                .phone("phone")
                .address("address")
                .type("type")
                .build());

        //when
        List<Store> storeList = storeRepository.findAll();

        //then
        Store store = storeList.get(0);

        System.out.println(">>>>>>>>>> createDate="+store.getCreatedDate()+", modifiedDate="+store.getModifiedDate());

        assertThat(store.getCreatedDate()).isAfter(now);
        assertThat(store.getModifiedDate()).isAfter(now);

    }
}
