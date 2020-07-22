package com.hyunro.bookmark.web;

import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.service.jwt.JwtService;
import com.hyunro.bookmark.service.user.UserService;
import com.hyunro.bookmark.web.dto.Result;
import com.hyunro.bookmark.web.dto.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final JwtService jwtService;

    private final UserService userService;

    @PostMapping("/api/v1/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto, HttpServletResponse response) {
        UserLoginResponseDto responseDto = null;
        User loginUser = null;

        loginUser = userService.findByEmail(requestDto.getEmail());
        if (loginUser==null || !loginUser.getPassword().equals(requestDto.getPassword())) {
            return new UserLoginResponseDto();
        }
        String jwt_token = jwtService.create("user", loginUser, "user");
        response.setHeader("Authorization", jwt_token);
//        System.out.println("login"+", "+jwt_token);
        return new UserLoginResponseDto(loginUser);
    }

    @GetMapping("/api/v1/user")
    @ResponseBody
    public Boolean findBy(String name, String email) {
        if (name != null && !name.equals("")) {
            User user = userService.findByName(name);
            System.out.println(name);
            System.out.println(user);
            if (user!=null) return true;
        } else if (email != null && !email.equals("")) {
            User user = userService.findByEmail(email);
            System.out.println(email);
            System.out.println(user);
            if (user!=null) return true;
        }
        return false;
    }

//    @GetMapping("/api/v1/user/email}")
//    @ResponseBody
//    public Boolean findByEmail (String email) {
//        User user = userService.findByEmail(email);
//        if (user==null) return true;
//        return false;
//    }

    @PostMapping("/api/v1/user")
    public Result save(@RequestBody UserSaveRequestDto requestDto,  HttpServletResponse response) {
        Result result = Result.successInstance();
        User loginUser = null;
        UserResponseDto responseDto = null;
        try {
            loginUser = userService.save(requestDto);
            responseDto = new UserResponseDto(loginUser);
        } catch (Exception e) {
            result.fail();
            return result;
        }
        String jwt_token = jwtService.create("user", loginUser, "user");
        response.setHeader("Authorization", jwt_token);
//        System.out.println("register"+", "+jwt_token);
        result.setData(responseDto);
        return result;
    }

    @PutMapping("/api/v1/user")
    @ResponseBody
    public String update(@RequestBody UserUpdateRequestDto requestDto) {
        if (requestDto.getName_new() != null) { // 닉네임 변경
            if (userService.findByName(requestDto.getName_new()) != null) {
                return "이미 사용중인 닉네임입니다.";
            }
        }
        User user = userService.findByEmail(requestDto.getEmail());
        if(requestDto.getPassword_old()!=user.getPassword()) {
            return "비밀번호가 틀렸습니다.";
        }
        try {
            user.update(requestDto);
        } catch (Exception e) {
            return "변경 실패";
        }
        return "변경 완료";
    }

    @DeleteMapping("api/v1/user/{id}")
    public Long delete (@PathVariable Long id) {
        userService.delete(id);
        return id;
    }
}
