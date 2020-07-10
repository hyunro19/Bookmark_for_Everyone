package com.hyunro.bookmark.web;

import com.hyunro.bookmark.config.auth.LoginUser;
import com.hyunro.bookmark.config.auth.TestUserService;
import com.hyunro.bookmark.config.auth.dto.SessionUser;
import com.hyunro.bookmark.service.posts.PostsService;
import com.hyunro.bookmark.web.dto.posts.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;
    private final TestUserService testUserService;

    @GetMapping("/") // 기본으로 index를 리턴
    public String index(Model model, @LoginUser SessionUser user) {
        // Model 서버 템블릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());

        // CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
        // 즉, 로그인 성공시 httpSession getAttribute("user")에서 값을 가져올 수 있다.
//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); 이 부분은 어노테이션으로 변환

        // 세션에 저장된 값이 있을 때만 model에 userName으로 등록
        // 값이 없는 경우엔 model에 아무 값이 없으므로, 로그인 버튼이 보인다.
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
        // MUSTACHE STARTER가 스트링을 리턴하면 경로/리턴값.mustache 를 자동으로 연결해준다.
        // src/main/resources/templates/index.mustache로 변환하여 ViewResolver가 처리
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        System.out.println("test controller");
        String test = "{1: \"item1\", 2: \"item2\"}";
        return test;
    }

    @GetMapping("/test_login")
    @ResponseBody
    public OAuth2User test_login() {
        return testUserService.loadUser();
    }

}
