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
        response.setHeader("authorization", jwt_token);
//        System.out.println("login"+", "+jwt_token);
        return new UserLoginResponseDto(loginUser);
    }

    @GetMapping("/api/v1/user")
    public UserResponseDto getUserInfo() {
        Long user_id = jwtService.getUserId();
        return userService.findById(user_id);
    }

    @GetMapping("/api/v1/user/if_exists")
    public boolean ifExists(String email, String name) {
        User user = null;
        if (email!="" && email!=null) {
            user = userService.findByEmail(email);
        } else if (name!="" && name!=null) {
            user = userService.findByName(name);
        }
        if (user==null) return false;
        else return true;
    }

    @PostMapping("/api/v1/user")
    public UserSaveResponseDto save(@RequestBody UserSaveRequestDto requestDto,  HttpServletResponse response) {
        User createdUser = null;
        UserSaveResponseDto responseDto = null;
        try {
            createdUser = userService.save(requestDto);
            responseDto = new UserSaveResponseDto(createdUser);
            String jwt_token = jwtService.create("user", createdUser, "user");
            response.setHeader("authorization", jwt_token);
            //  System.out.println("register"+", "+jwt_token);
        } catch (Exception e) {
            responseDto = new UserSaveResponseDto();
            return responseDto;
        }

        return responseDto;
    }

    @PutMapping("/api/v1/user")
    public String update(@RequestBody UserUpdateRequestDto requestDto, HttpServletResponse response) {
        System.out.println(requestDto.getEmail());
        System.out.println(requestDto.getName_new());
        System.out.println(requestDto.getPassword_old());
        System.out.println(requestDto.getPassword_new());
        User user = userService.findByEmail(requestDto.getEmail());
        if(!requestDto.getPassword_old().equals(user.getPassword())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return "비밀번호가 틀렸습니다.";
        }

        // 닉네임 변경
        if (requestDto.getName_new() != null && !requestDto.getName_new().equals("")) {
            if (userService.findByName(requestDto.getName_new()) != null) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return "이미 사용중인 닉네임입니다.";
            }
        }

        try {
            userService.update(user.getUser_id(), requestDto);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return "변경에 실패하였습니다.\n다시 시도해주세요.";
        }
        return "변경을 완료하였습니다.";
    }

    @DeleteMapping("api/v1/user/{id}")
    public Long delete (@PathVariable Long id) {
        userService.delete(id);
        return id;
    }
}
