package com.hyunro.bookmark.domain.comment;

import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.bookmark.BookmarkRepository;
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
public class CommentRepositoryTest {

    User user;
    Bookmark bookmark;

    @Autowired
    CommentRepository commentRepository;

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
        commentRepository.deleteAll();
    }

    @Test
    public void 코멘트저장_불러오기() {
        //given
        String user_name = "테스트 유저이름";

        String content = "테스트 콘텐트";

        // 테이블 bookmark에 insert/update 쿼리를 실행 id가 있다면 update, 없다면 insert
        commentRepository.save(Comment.builder()
                .user(user)
                .user_name(user_name)
                .bookmark(bookmark)
                .content(content)
                .build());

        //when
        List<Comment> commentList = commentRepository.findAll(); // 테이블 posts의 모든 데이터를 조회

        //then
        Comment comment = commentList.get(0);
        assertThat(comment.getUser().getName()).isEqualTo(user.getName());
        assertThat(comment.getUser_name()).isEqualTo(user_name);
        assertThat(comment.getBookmark().getUrl()).isEqualTo("테스트 URL");
        assertThat(comment.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given

        String user_name = "테스트 유저이름";

        String content = "테스트 콘텐트";

        LocalDateTime now = LocalDateTime.of(2020,5, 6, 0, 0, 0);
        commentRepository.save(Comment.builder()
                .user(user)
                .user_name(user_name)
                .bookmark(bookmark)
                .content(content)
                .build());

        //when
        List<Comment> commentList = commentRepository.findAll();

        //Bookmark
        Comment comment = commentList.get(0);

        System.out.println(">>>>>>>>>> createDate="+comment.getCreatedDate()+", modifiedDate="+comment.getModifiedDate());

        assertThat(comment.getCreatedDate()).isAfter(now);
        assertThat(comment.getModifiedDate()).isAfter(now);

    }
}
