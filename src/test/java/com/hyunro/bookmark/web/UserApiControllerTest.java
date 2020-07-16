package com.hyunro.bookmark.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.domain.user.UserRepository;
import com.hyunro.bookmark.web.dto.user.UserSaveRequestDto;
import com.hyunro.bookmark.web.dto.user.UserUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest를 사용하지 않는다.
// @WebMvcTest는 JPA기능이 작동하지 않기 때문
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc= MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void createUser() throws Exception {
        //given
        String name = "name";
        String email = "test@gmail.com";
        String password = "password";
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();

        String url = "http://localhost:"+port+"/api/v1/user";

//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //when
        mvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(new ObjectMapper().writeValueAsString(requestDto)))
        .andExpect(status().isOk());

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getEmail()).isEqualTo(email);
        assertThat(all.get(0).getPassword()).isEqualTo(password);
    }

    @Test
    public void updateUser() throws Exception {
        //given
        User savedUser = userRepository.save(User.builder()
            .name("name")
            .email("test@gmail.com")
            .password("password")
            .build());

        Long updateId = savedUser.getId();
        String expectedName = "name2";
        String expectedPassword = "password2";

        UserUpdateRequestDto requestDto = UserUpdateRequestDto.builder()
                .name(expectedName)
                .password(expectedPassword)
                .build();

        String url = "http://localhost:"+port+"/api/v1/user/"+updateId;

        HttpEntity<UserUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
//
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //when
        mvc.perform(put(url)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(new ObjectMapper().writeValueAsString(requestDto)))
        .andExpect(status().isOk());
        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(expectedName);
        assertThat(all.get(0).getPassword()).isEqualTo(expectedPassword);

    }

}
