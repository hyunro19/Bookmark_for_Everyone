package com.hyunro.tabmenu.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunro.tabmenu.domain.store.Store;
import com.hyunro.tabmenu.domain.store.StoreRepository;
import com.hyunro.tabmenu.web.dto.store.StoreSaveRequestDto;
import com.hyunro.tabmenu.web.dto.store.StoreUpdateRequestDto;
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

// @WebMvcTest를 사용하지 않는다.
// @WebMvcTest는 JPA기능이 작동하지 않기 때문
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc= MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        storeRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void addStore() throws Exception {
        //given
        String name = "name";
        String phone = "phone";
        String address = "address";
        String type = "type";
        StoreSaveRequestDto requestDto = StoreSaveRequestDto.builder()
                .name(name)
                .phone(phone)
                .address(address)
                .type(type)
                .build();

        String url = "http://localhost:"+port+"/api/v1/store";

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

        List<Store> all = storeRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getPhone()).isEqualTo(phone);
        assertThat(all.get(0).getAddress()).isEqualTo(address);
        assertThat(all.get(0).getType()).isEqualTo(type);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Store_수정된다() throws Exception {
        //given
        Store savedStore = storeRepository.save(Store.builder()
                .name("name")
                .phone("phone")
                .address("address")
                .type("type")
                .build());

        Long updateId = savedStore.getId();
        String expectedName = "name2";
        String expectedPhone = "phone2";
        String expectedAddress = "address2";
        String expectedType = "type2";

        StoreUpdateRequestDto requestDto = StoreUpdateRequestDto.builder()
                .name(expectedName)
                .phone(expectedPhone)
                .address(expectedAddress)
                .type(expectedType)
                .build();

        String url = "http://localhost:"+port+"/api/v1/store/"+updateId;

        HttpEntity<StoreUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

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
        List<Store> all = storeRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(expectedName);
        assertThat(all.get(0).getPhone()).isEqualTo(expectedPhone);
        assertThat(all.get(0).getAddress()).isEqualTo(expectedAddress);
        assertThat(all.get(0).getType()).isEqualTo(expectedType);

    }

}
