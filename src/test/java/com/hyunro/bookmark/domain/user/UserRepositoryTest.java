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

//    @Autowired
//    UserRepository userRepository;
//
//    @After
//    public void cleanup() {
//        userRepository.deleteAll();
//    }
//
//    @Test
//    public void 유저저장_불러오기() {
//        //given
//
//        // 테이블 bookmark에 insert/update 쿼리를 실행 id가 있다면 update, 없다면 insert
//        userRepository.save(User.builder()
//                .name("테스트 네임")
//                .email("hyunro19@gmail.com")
//                .password("테스트 비밀번호")
//                .build());
//
//        //when
//        List<User> userList = userRepository.findAll();
//
//        //then
//        User user = userList.get(0);
//        assertThat(user.getName()).isEqualTo("테스트 네임");
//        assertThat(user.getEmail()).isEqualTo("hyunro19@gmail.com");
//        assertThat(user.getPassword()).isEqualTo("테스트 비밀번호");
//    }
//
//    @Test
//    public void 유저_갱신하기() {
//        User user = User.builder()
//                .name("테스트 네임")
//                .email("hyunro19@gmail.com")
//                .password("테스트 비밀번호")
//                .build();
//        userRepository.save(user);
//
//        user.update("수정 후 네임", "수정 후 비밀번호");
//        userRepository.save(user);
//
//        //when
//        List<User> userList = userRepository.findAll();
//
//        //Bookmark
//        User user2 = userList.get(0);
//        assertThat(user2.getName()).isEqualTo("수정 후 네임");
//        assertThat(user2.getPassword()).isEqualTo("수정 후 비밀번호");
//    }

}
