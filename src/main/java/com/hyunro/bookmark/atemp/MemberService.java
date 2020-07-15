package com.hyunro.bookmark.atemp;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    MemberMaster signup(MemberMaster memberMaster);

    boolean isExist(String email);

    void validate(String email);

    MemberMaster signin(String email, String password);

    void updateInfo(String nickname, String password, int memberId);

    MemberMaster findByMemberId(int memberId);

    String uploadProfileImg(String encodeImg, int memberId);
}
