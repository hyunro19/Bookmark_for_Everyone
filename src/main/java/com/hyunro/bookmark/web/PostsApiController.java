package com.hyunro.bookmark.web;

import com.hyunro.bookmark.service.jwt.JwtService;
import com.hyunro.bookmark.service.posts.PostsService;
import com.hyunro.bookmark.web.dto.posts.*;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    // @Autowired대신 @RequiredArgsConstructor가 생성자 주입 방식으로 Bean객체를 연결해준다.
    // final이 선언도니 모든 필드를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConstructor가 대신 생성해 줌
    private final PostsService postsService;
    private final JwtService jwtService;

    @GetMapping("api/v1/posts_url")
    public PostsUrlResponseDto requetPostsUrl(String src_url) {
        System.out.println(src_url);
//        src_url = requestDto.getSrc_url();
        String src_title = null;
        String src_description = null;
        String src_img = null;

        // 이미지 주소를 담을 리스트 생성
        try {
            Document document = Jsoup.connect(src_url).get();

            Element meta_title = document.select("meta[property=og:title]").first();
            Element meta_description = document.select("meta[property=og:description]").first();
            Element meta_image = document.select("meta[property=og:image]").first();

            src_title = meta_title.attr("content");
            src_description = meta_description.attr("content");
            src_img = meta_image.attr("content");

        }catch(IOException e){
            e.printStackTrace();
        }

        return PostsUrlResponseDto.builder()
                .src_title(src_title)
                .src_description(src_description)
                .src_img(src_img)
                .build();
    }

    @GetMapping("api/v1/posts")
    public List<PostsListResponseDto> findAllDesc(HttpServletResponse response) {
        return postsService.findAllDesc();
    }

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        // request의 userid랑 같은지 확인
        Long token_id = Long.valueOf( (Integer)jwtService.get("user").get("user_id") );
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
