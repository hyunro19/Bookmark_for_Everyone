package com.hyunro.bookmark.domain.bookmark;

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
public class BookmarkRepositoryTest {

    User user;

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
    }

    @After
    public void cleanup() {
        bookmarkRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void 북마크저장_불러오기() {
        //given
        String topic = "테스트 토픽";
        String url = "테스트 유알엘";
        String content = "테스트 내용";

        bookmarkRepository.save(Bookmark.builder()
                .user(user)
                .user_name(user.getName())
                .topic(topic)
                .url(url)
                .content(content)
                .build());

        //when
        List<Bookmark> bookmarkList = bookmarkRepository.findAll();

        //then
        Bookmark bookmark = bookmarkList.get(0);
        assertThat(bookmark.getUser().getId()).isEqualTo(user.getId());
        assertThat(bookmark.getTopic()).isEqualTo(topic);
        assertThat(bookmark.getUrl()).isEqualTo(url);
        assertThat(bookmark.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        String topic = "테스트 토픽";
        String url = "테스트 유알엘";
        String content = "테스트 내용";
        LocalDateTime now = LocalDateTime.of(2020,5, 6, 0, 0, 0);
        bookmarkRepository.save(Bookmark.builder()
                .user(user)
                .user_name(user.getName())
                .topic(topic)
                .url(url)
                .content(content)
                .build());

        //when
        List<Bookmark> bookmarkList = bookmarkRepository.findAll();

        //Bookmark
        Bookmark bookmark = bookmarkList.get(0);

        System.out.println(">>>>>>>>>> createDate="+bookmark.getCreatedDate()+", modifiedDate="+bookmark.getModifiedDate());

        assertThat(bookmark.getCreatedDate()).isAfter(now);
        assertThat(bookmark.getModifiedDate()).isAfter(now);

    }
}
