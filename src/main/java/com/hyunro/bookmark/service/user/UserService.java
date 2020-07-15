package com.hyunro.bookmark.service.user;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.hyunro.bookmark.atemp.MemberMaster;
import com.hyunro.bookmark.domain.user.Role;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service("memberService")
public class UserService {

    private static final String DEFAULT_NICKNAME = "모두의북마크";
    private static final String SIGNIN_EXCEPTION_MSG = "로그인정보가 일치하지 않습니다.";
    private static final String EMAIL_EXIST_EXCEPTION_MSG = "이미 계정이 존재합니다.";
    private static final String NICKNAME_EXIST_EXCEPTION_MSG = "이미 존재하는 이름입니다.";

    @Autowired
    private UserRepository userRepository;

    public User signup(User user) {
        String email = user.getEmail();
        this.validate(email);
        this.setupForSave(user);
        User createdUser = userRepository.save(user);

//        int memberId = createdUser.getMemberId();
//        createdUser.setNickname(memberId + DEFAULT_NICKNAME);
//        userRepository.save(memberMaster);
        return createdUser;
    }

    private void setupForSave(User user){
//        String password = user.getPassword();
//        String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
//        user.setPassword(encodedPassword);
//
//        user.setMemberType(Role.GUEST);
//        EntityUtils.initializeRegAndModDate(memberMaster);
    }

    public boolean isExist(String email) {
        boolean isExist = false;
        User user = userRepository.findByEmail(email);
        if(user != null){
            isExist = true;
        }
        return isExist;
    }

    public void validate(String email){
        if( this.isExist(email) ){
            throw new IllegalStateException(EMAIL_EXIST_EXCEPTION_MSG);
        }
    }

    public User signin(String email, String password) {
        User user = userRepository.findByEmail(email);
        Objects.requireNonNull(user, SIGNIN_EXCEPTION_MSG);

        if( ! this.isAccordPassword(user, password)){
            throw new IllegalStateException(SIGNIN_EXCEPTION_MSG);
        }

        return user;
    }

    private boolean isAccordPassword(User user, String password){
        String encodedPassword = user.getPassword();
        return BCrypt.checkpw(password, encodedPassword);
    }

//    public void updateInfo(String nickname, String password, Long userId) {
//        User currentUser = userRepository.getOne(userId);
//        this.updateNickname(currentUser, nickname);
////        this.updatePassword(currentUser, password);
//        userRepository.save(currentUser);
//    }

//    private void updateNickname(User memberMaster, String nickname){
//        String currentNickname = memberMaster.getNickname();
//        MemberMaster searchedMember = userRepository.findByNickname(nickname);
//        if(currentNickname.equals(nickname) || searchedMember == null ){
//            memberMaster.setNickname(nickname);
//        }else{
//            throw new IllegalStateException(NICKNAME_EXIST_EXCEPTION_MSG);
//        }
//    }

    private void updatePassword(MemberMaster memberMaster, String password){
        if(!password.equals("")){
            String encodePassword = BCrypt.hashpw(password, BCrypt.gensalt());
            memberMaster.setPassword(encodePassword);
        }
    }

    public User findByMemberId(Long userId) {
        return userRepository.findById(userId).get();
    }

}

