package com.hyunro.bookmark.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.bookmark.BookmarkRepository;
import com.hyunro.bookmark.domain.user.Role;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.domain.user.UserRepository;
import com.hyunro.bookmark.web.dto.bookmark.BookmarkSaveRequestDto;
import com.hyunro.bookmark.web.dto.bookmark.BookmarkUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
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
public class BookmarkApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    private User user;

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
    }

    @After
    public void tearDown() throws Exception {
        bookmarkRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="GUEST")
    public void Bookmark_등록된다() throws Exception {
        //given
        String topic = "topic";
        String url1 = "url";
        String src_title = "src_title";
        String src_description = "src_description";
        String content = "content";
        Integer number_comment = 0;
        Integer number_thumb = 0;
        Integer number_share = 0;
        Integer is_public = 0;

        BookmarkSaveRequestDto requestDto = BookmarkSaveRequestDto.builder()
//                .user(user)
//                .user_name(user.getName())
                .topic(topic)
                .url(url1)
                .src_title(src_title)
                .src_description(src_description)
                .content(content)
                .number_comment(number_comment)
                .number_thumb(number_thumb)
                .number_share(number_share)
                .is_public(is_public)
                .build();

        String url = "http://localhost:"+port+"/api/v1/bookmark";

        //when
        mvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(new ObjectMapper().writeValueAsString(requestDto)))
        .andExpect(status().isOk());

        List<Bookmark> all = bookmarkRepository.findAll();
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser(roles="GUEST")
    public void Bookmark_수정된다() throws Exception {
        //given
        String topic = "테스트 토픽";
        String url1 = "테스트 유알엘";
        String content = "테스트 내용";

        Bookmark savedBookmark = bookmarkRepository.save(Bookmark.builder()
                .topic(topic)
                .url(url1)
                .content(content)
            .build());

        Long updateId = savedBookmark.getId();
        String expectedTopic = "테스트 토픽2";
        String expectedUrl = "테스트 유알엘2";
        String expectedContent = "테스트 내용2";
        BookmarkUpdateRequestDto requestDto = BookmarkUpdateRequestDto.builder()
                .topic(expectedTopic)
                .url(expectedUrl)
                .content(expectedContent)
                .build();


        String url = "http://localhost:"+port+"/api/v1/bookmark/"+updateId;

        HttpEntity<BookmarkUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        mvc.perform(put(url)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(requestDto)))
            .andExpect(status().isOk());
        List<Bookmark> all = bookmarkRepository.findAll();
        assertThat(all.get(0).getTopic()).isEqualTo(expectedTopic);
        assertThat(all.get(0).getUrl()).isEqualTo(expectedUrl);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }

}
