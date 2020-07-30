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

    private final PostsService postsService;
    private final JwtService jwtService;

    @GetMapping("api/v1/posts_url")
    public PostsUrlResponseDto requetPostsUrl(String src_url) {
        if(!src_url.startsWith("http")) {
            src_url = "http://"+src_url;
        }

        String src_title = null;
        String src_description = null;
        String src_img = null;

        try {
            Document document = Jsoup.connect(src_url).get();

            Element meta_title = document.select("meta[property=og:title]").first();
            Element meta_description = document.select("meta[property=og:description]").first();
            Element meta_image = document.select("meta[property=og:image]").first();

            if(meta_title != null) {
                src_title = meta_title.attr("content");
            } else {
                src_title = "";
            }
            if(meta_description != null) {
                src_description = meta_description.attr("content");
            } else {
                src_description = "";
            }
            if(meta_image != null) {
                src_img = meta_image.attr("content");
            } else {
                src_img = "https://d1nhio0ox7pgb.cloudfront.net/_img/o_collection_png/green_dark_grey/512x512/plain/book_bookmark.png";
            }


        }catch(IOException e){
            e.printStackTrace();
        }

        return PostsUrlResponseDto.builder()
                .src_url(src_url)
                .src_title(src_title)
                .src_description(src_description)
                .src_img(src_img)
                .build();
    }

    @GetMapping("api/v1/posts_list/{sort}")
    public List<PostsListResponseDto> findAllDesc(@PathVariable("sort") String sort) {
        System.out.println("PathVariable sort : "+sort);
        if (sort.equals("recent")) {
            return postsService.findAllDesc();
        } else if (sort.equals("my")) {
            Long user_id = jwtService.getUserId();
            return postsService.findAllByUserId(user_id);
        } else {
            return postsService.findAllByTopic(sort);
        }


    }

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
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

    @DeleteMapping("api/v1/posts/{posts_id}")
    public Long delete (@PathVariable Long posts_id) {
        Long user_id = jwtService.getUserId();
        postsService.delete(posts_id, user_id);
        return posts_id;
    }
}
