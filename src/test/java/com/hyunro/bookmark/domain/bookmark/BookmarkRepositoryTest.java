package com.hyunro.bookmark.domain.bookmark;

import com.hyunro.bookmark.domain.posts.Posts;
import com.hyunro.bookmark.domain.user.Role;
import com.hyunro.bookmark.domain.user.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookmarkRepositoryTest {

    @Autowired
    BookmarkRepository bookmarkRepository;

    @After // Junit에서 단위테스트가 끝날때마다 수행되는 메소드를 지정
    public void cleanup() {
        bookmarkRepository.deleteAll();
    }

    @Test
    public void 북마크저장_불러오기() {
        //given
        User user = User.builder().name("테스트 유저").email("테스트 이메일").picture("테스트 사진").role(Role.GUEST).build();
        String user_name = "테스트 유저 네임";
        String topic = "테스트 토픽";
        String url = "테스트 유알엘";
        String content = "테스트 내용";

        // 테이블 bookmark에 insert/update 쿼리를 실행 id가 있다면 update, 없다면 insert
        bookmarkRepository.save(Bookmark.builder()
                .user(user)
                .user_name(user_name)
                .topic(topic)
                .url(url)
                .content(content).build());

        //when
        List<Bookmark> bookmarkList = bookmarkRepository.findAll(); // 테이블 posts의 모든 데이터를 조회

        //then
        Bookmark bookmark = bookmarkList.get(0);
        assertThat(bookmark.getUser().getName()).isEqualTo(user.getName());
        assertThat(bookmark.getUser_name()).isEqualTo(user_name);
        assertThat(bookmark.getTopic()).isEqualTo(topic);
        assertThat(bookmark.getUrl()).isEqualTo(url);
        assertThat(bookmark.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        User user = User.builder().name("테스트 유저").email("테스트 이메일").picture("테스트 사진").role(Role.GUEST).build();
        String user_name = "테스트 유저 네임";
        String topic = "테스트 토픽";
        String url = "테스트 유알엘";
        String content = "테스트 내용";
        LocalDateTime now = LocalDateTime.of(2020,5, 6, 0, 0, 0);
        bookmarkRepository.save(Bookmark.builder()
                .user(user)
                .user_name(user_name)
                .topic(topic)
                .url(url)
                .content(content).build());

        //when
        List<Bookmark> bookmarkList = bookmarkRepository.findAll();

        //Bookmark
        Bookmark bookmark = bookmarkList.get(0);

        System.out.println(">>>>>>>>>> createDate="+bookmark.getCreatedDate()+", modifiedDate="+bookmark.getModifiedDate());

        assertThat(bookmark.getCreatedDate()).isAfter(now);
        assertThat(bookmark.getModifiedDate()).isAfter(now);

    }
}
