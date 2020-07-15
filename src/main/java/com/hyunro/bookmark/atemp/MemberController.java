package com.hyunro.bookmark.atemp;


import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    public Result signup(@RequestBody MemberMaster memberMaster){
        Result result = Result.successInstance();
        MemberMaster createdMember = memberService.signup(memberMaster);
        result.setData(createdMember);
        return result;
    }

    @GetMapping("/validate")
    public Result validate(String email){
        memberService.validate(email);
        Result result = Result.successInstance();
        return result;
    }

    @PostMapping(value="/signin")
    public Result signin(String email, String password, HttpServletResponse response){
        System.out.println("LOG--"+email+", "+password);
        Result result = Result.successInstance();
        MemberMaster loginMember = memberService.signin(email, password);
        String token = jwtService.create("member", loginMember, "user");
        response.setHeader("Authorization", token);
        result.setData(loginMember);
        return result;
    }

    @PostMapping(value="/signin/jwt")
    public Result signin(){
        Result result = Result.successInstance();
        int memberId = jwtService.getMemberId();
        MemberMaster loginMember = memberService.findByMemberId(memberId);
        result.setData(loginMember);
        return result;
    }

    @PutMapping(value="/info")
    public Result updateInfo(String nickname, String password){
        Result result = Result.successInstance();
        int memberId = jwtService.getMemberId();
        memberService.updateInfo(nickname, password, memberId);
        return result;

    }

    @PostMapping(value="/info/img")
    public Result uploadProfileImg(@RequestBody Map<String, Object> encodeImg){
        Result result = Result.successInstance();
        int memberId = jwtService.getMemberId();
        String profileImg = memberService.uploadProfileImg(encodeImg.get("encodeImg").toString(), memberId);
        result.setData(profileImg);
        return result;
    }
}
