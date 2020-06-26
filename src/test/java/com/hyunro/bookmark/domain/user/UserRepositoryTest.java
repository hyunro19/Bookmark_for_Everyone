package com.hyunro.bookmark.domain.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void 유저저장_불러오기() {
        //given

        // 테이블 bookmark에 insert/update 쿼리를 실행 id가 있다면 update, 없다면 insert
        userRepository.save(User.builder()
                .name("테스트 네임")
                .email("테스트 이메일")
                .picture("테스트 사진")
                .role(Role.GUEST)
                .build());

        //when
        List<User> userList = userRepository.findAll();

        //then
        User user = userList.get(0);
        assertThat(user.getName()).isEqualTo("테스트 네임");
        assertThat(user.getEmail()).isEqualTo("테스트 이메일");
        assertThat(user.getPicture()).isEqualTo("테스트 사진");
        assertThat(user.getRole()).isEqualTo(Role.GUEST);
    }

    @Test
    public void 유저_갱신하기() {
        User user = User.builder()
                .name("테스트 네임")
                .email("테스트 이메일")
                .picture("테스트 사진")
                .role(Role.GUEST)
                .build();
        userRepository.save(user);

        user.update("수정후 네임", "수정후 사진");
        userRepository.save(user);

        //when
        List<User> userList = userRepository.findAll();

        //Bookmark
        User user2 = userList.get(0);
        assertThat(user2.getName()).isEqualTo("수정후 네임");
        assertThat(user2.getPicture()).isEqualTo("수정후 사진");
    }

}
