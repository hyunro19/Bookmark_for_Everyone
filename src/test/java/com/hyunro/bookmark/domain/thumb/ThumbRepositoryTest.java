package com.hyunro.bookmark.domain.thumb;

import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.bookmark.BookmarkRepository;
import com.hyunro.bookmark.domain.user.Role;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.domain.user.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Before
    public void setup() {
      user = User.builder()
              .name("테스트 유저")
              .email("테스트 이메일")
              .picture("테스트 사진")
              .role(Role.GUEST)
              .build();
      userRepository.save(user);
      bookmark = Bookmark.builder()
            .user(user)
            .user_name("테스트 유저이름")
            .topic("테스트 토픽")
            .url("테스트 URL")
            .content("테스트 내용")
            .build();
      bookmarkRepository.save(bookmark);
    }
    @After
    public void cleanup() {
        thumbRepository.deleteAll();
        bookmarkRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void 라이크저장_불러오기() {
        thumbRepository.save(Thumb.builder()
                .user(user)
                .bookmark(bookmark)
                .build());

        //when
        List<Thumb> thumbList = thumbRepository.findAll();

        //then
        Thumb thumb = thumbList.get(0);
        assertThat(thumb.getUser().getId()).isEqualTo(user.getId());
        assertThat(thumb.getBookmark().getId()).isEqualTo(bookmark.getId());
    }
}
