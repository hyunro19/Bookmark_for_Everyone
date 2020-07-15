package com.hyunro.bookmark.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.bookmark.BookmarkRepository;
import com.hyunro.bookmark.domain.thumb.Thumb;
import com.hyunro.bookmark.domain.thumb.ThumbRepository;
import com.hyunro.bookmark.domain.user.Role;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.domain.user.UserRepository;
import com.hyunro.bookmark.web.dto.thumb.ThumbSaveRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ThumbApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private ThumbRepository thumbRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    private User user;
    private Bookmark bookmark;

    @Before
    public void setup() {
        mvc= MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        user = User.builder()
                .name("테스트 유저")
                .email("테스트 이메일")
                .picture("테스트 사진")
                .role(Role.GUEST)
                .build();
        userRepository.save(user);
        user = userRepository.findAll().get(0);
        bookmark = Bookmark.builder()
                .user(user)
                .topic("테스트 토픽")
                .url("테스트 유알엘")
                .content("테스트 내용")
                .build();
        bookmarkRepository.save(bookmark);
        bookmark = bookmarkRepository.findAll().get(0);
    }

    @After
    public void tearDown() throws Exception {
        thumbRepository.deleteAll();
        bookmarkRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="GUEST")
    public void Thumb_등록된다() throws Exception {
        //given
        Long bookmark_id = bookmark.getId();

        ThumbSaveRequestDto requestDto = ThumbSaveRequestDto.builder()
                .bookmark_id(bookmark_id)
                .build();

        String url = "http://localhost:"+port+"/api/v1/thumb";

        //when
        mvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(new ObjectMapper().writeValueAsString(requestDto)))
        .andExpect(status().isOk());

        List<Thumb> all = thumbRepository.findAll();
        assertThat(all.get(0).getUser().getId()).isEqualTo(user.getId());
        assertThat(all.get(0).getBookmark().getId()).isEqualTo(bookmark.getId());
    }

}
