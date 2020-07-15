package com.hyunro.bookmark.web;


import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hyunro.bookmark.atemp.JwtService;
import com.hyunro.bookmark.atemp.MemberMaster;
import com.hyunro.bookmark.atemp.Result;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vivie on 2017-06-08.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    public Result signup(@RequestBody User user){
        Result result = Result.successInstance();
        User createdUser = userService.signup(user);
        result.setData(createdUser);
        return result;
    }

    @GetMapping("/validate")
    public Result validate(String email){
        userService.validate(email);
        Result result = Result.successInstance();
        return result;
    }

    @PostMapping(value="/signin")
    public Result signin(String email, String password, HttpServletResponse response){
        Result result = Result.successInstance();
        User loginUser = userService.signin(email, password);
        String token = jwtService.create("member", loginUser, "user");
        response.setHeader("Authorization", token);
        result.setData(loginUser);
        return result;
    }

    @PostMapping(value="/signin/jwt")
    public Result signin(){
        Result result = Result.successInstance();
        Long userId = jwtService.getMemberId();
        User loginUser = userService.findByMemberId(userId);
        result.setData(loginUser);
        return result;
    }

    @PutMapping(value="/info")
    public Result updateInfo(String name, String password){
        Result result = Result.successInstance();
        Long user_id = jwtService.getMemberId();
//        userService.updateInfo(name, password, user_id);
        return result;

    }
}

