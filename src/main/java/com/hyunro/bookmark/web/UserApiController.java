package com.hyunro.bookmark.web;

import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.service.jwt.JwtService;
import com.hyunro.bookmark.service.user.UserService;
import com.hyunro.bookmark.web.dto.Result;
import com.hyunro.bookmark.web.dto.user.UserLoginRequestDto;
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

    @PostMapping("/api/v1/login")
    public Result login(@RequestBody UserLoginRequestDto requestDto, HttpServletResponse response) {
        Result result = Result.successInstance();

        User loginUser = null;
        UserResponseDto responseDto = null;
        try {
            loginUser = userService.findByEmail(requestDto.getEmail());
            responseDto = new UserResponseDto(loginUser);
        } catch (Exception e) {
            result.fail();
            return result;
        }
        if(loginUser==null) {
            result.fail();
        }
        String jwt_token = jwtService.create("user", loginUser, "user");
        response.setHeader("jwt_token", jwt_token);
//        System.out.println("login"+", "+jwt_token);
        result.setData(responseDto);
        return result;
    }

    @GetMapping("/api/v1/user/{id}")
    public UserResponseDto findById (@PathVariable Long id) {
        return userService.findById(id);
    }

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
        response.setHeader("jwt_token", jwt_token);
//        System.out.println("register"+", "+jwt_token);
        result.setData(responseDto);
        return result;
    }

    @PutMapping("/api/v1/user")
    @ResponseBody
    public String update(@RequestBody UserUpdateRequestDto requestDto, HttpServletResponse response) {
        
        Result result = Result.successInstance();
        try {
            User user = userService.findByEmail(requestDto.getEmail());
            if (user.getPassword().equals(requestDto.getPassword_old())) {
                user.update(requestDto);
            } else {
                throw new Exception();
            }
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
