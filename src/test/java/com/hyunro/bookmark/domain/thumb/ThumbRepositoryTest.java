package com.hyunro.bookmark.domain.thumb;

import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.user.Role;
import com.hyunro.bookmark.domain.user.User;
import org.junit.After;
import org.junit.Before;
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
public class ThumbRepositoryTest {

    User user;
    Bookmark bookmark;

    @Autowired
    ThumbRepository thumbRepository;

    @Before
    public void setup() {
      user = User.builder()
              .name("테스트 유저")
              .email("테스트 이메일")
              .picture("테스트 사진")
              .role(Role.GUEST)
              .build();
      bookmark = Bookmark.builder()
            .user(user)
            .user_name("테스트 유저이름")
            .topic("테스트 토픽")
            .url("테스트 URL")
            .content("테스트 내용")
            .build();
    }
    @After // Junit에서 단위테스트가 끝날때마다 수행되는 메소드를 지정
    public void cleanup() {
        thumbRepository.deleteAll();
    }

    @Test
    public void 라이크저장_불러오기() {
        //given

        // 테이블 bookmark에 insert/update 쿼리를 실행 id가 있다면 update, 없다면 insert
        thumbRepository.save(Thumb.builder()
                .user(user)
                .bookmark(bookmark)
                .build());

        //when
        List<Thumb> thumbList = thumbRepository.findAll();

        //then
        Thumb thumb = thumbList.get(0);
        assertThat(thumb.getUser().getName()).isEqualTo(user.getName());
        assertThat(thumb.getBookmark().getUrl()).isEqualTo(bookmark.getUrl());
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given

        LocalDateTime now = LocalDateTime.of(2020,5, 6, 0, 0, 0);
        thumbRepository.save(Thumb.builder()
                .user(user)
                .bookmark(bookmark)
                .build());

        //when
        List<Thumb> thumbList = thumbRepository.findAll();

        //Bookmark
        Thumb thumb = thumbList.get(0);

        System.out.println(">>>>>>>>>> createDate="+ thumb.getCreatedDate()+", modifiedDate="+ thumb.getModifiedDate());

        assertThat(thumb.getCreatedDate()).isAfter(now);
        assertThat(thumb.getModifiedDate()).isAfter(now);

    }
}
