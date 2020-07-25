package com.hyunro.bookmark.web;

import com.hyunro.bookmark.service.jwt.JwtService;
import com.hyunro.bookmark.service.posts.PostsService;
import com.hyunro.bookmark.web.dto.posts.PostsListResponseDto;
import com.hyunro.bookmark.web.dto.posts.PostsResponseDto;
import com.hyunro.bookmark.web.dto.posts.PostsSaveRequestDto;
import com.hyunro.bookmark.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    // @Autowired대신 @RequiredArgsConstructor가 생성자 주입 방식으로 Bean객체를 연결해준다.
    // final이 선언도니 모든 필드를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConstructor가 대신 생성해 줌
    private final PostsService postsService;
    private final JwtService jwtService;

    @GetMapping("api/v1/posts")
    public List<PostsListResponseDto> findAllDesc(HttpServletResponse response) {
        return postsService.findAllDesc();
    }

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        // request의 userid랑 같은지 확인
        Long token_id = Long.valueOf( (Integer)jwtService.get("user").get("id") );
        Long request_id = requestDto.getUser_id();
        System.out.println("token_id : "+token_id+", request_id : "+request_id);
        if (token_id != request_id) return 0L;

        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("api/v1/posts/{id}")
    public Long delete (@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
