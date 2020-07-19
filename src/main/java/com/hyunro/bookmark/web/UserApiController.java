package com.hyunro.bookmark.web;

import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.service.jwt.JwtService;
import com.hyunro.bookmark.service.user.UserService;
import com.hyunro.bookmark.web.dto.Result;
import com.hyunro.bookmark.web.dto.user.UserResponseDto;
import com.hyunro.bookmark.web.dto.user.UserSaveRequestDto;
import com.hyunro.bookmark.web.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final JwtService jwtService;

    private final UserService userService;

    @PostMapping("/api/v1/user")
    public Result save(@RequestBody UserSaveRequestDto requestDto,  HttpServletResponse response) {
        Result result = Result.successInstance();
        User loginUser = userService.save(requestDto);
        String token = jwtService.create("user", loginUser, "user");
        System.out.println("------------------token : "+token);
        response.setHeader("Authorization", token);
        result.setData(loginUser);
        return result;
    }

    @PutMapping("/api/v1/user/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto) {
        return userService.update(id, requestDto);
    }

    @GetMapping("api/v1/user/{id}")
    public UserResponseDto findById (@PathVariable Long id) {
        return userService.findById(id);
    }

    @DeleteMapping("api/v1/user/{id}")
    public Long delete (@PathVariable Long id) {
        userService.delete(id);
        return id;
    }
}
