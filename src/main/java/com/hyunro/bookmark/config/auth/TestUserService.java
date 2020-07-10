package com.hyunro.bookmark.config.auth;

import com.hyunro.bookmark.config.auth.dto.SessionUser;
import com.hyunro.bookmark.domain.user.Role;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class TestUserService {
    // 구글 로그인 이후 가져온 사용자의 정보(email, name, picture)등 들을 기반으로 가입 및 정보수정, 세션 저장 등의 기능 지원

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    public OAuth2User loadUser() {
//        OAuth2UserService delegate = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        // registrationId 현재 로그인 진행 중인 서비스를 구분하는 코드
//        // 이후 구글인지 네이버인지 구분하는 값으로 사용
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//
//        // userNameAttributeName
//        // OAuth2 로그인 진행 시 키가 되는 필드값 (=PrimaryKey)
//        // 구글은 기본 코드를 지원하지만, 네이버, 카카오등은 지원하지 않아서 구글과 기타 로그인 동시 지원시 사용.
//        String userNameAttributeName = userRequest
//                .getClientRegistration()
//                .getProviderDetails()
//                .getUserInfoEndpoint()
//                .getUserNameAttributeName();
//
//        // OAuthAttributes
//        // OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
//        // 네이버 등 다른 소셜 로그인도 이 클래스를 사용
//        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate();

        // SessionUser
        // 세션에 사용자 정보를 저장하기 위한 Dto클래스
        // 기존 User클래스를 쓰지 않고 새로 만들어서 쓴다.
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                new HashMap<String, Object>(),
                "");
    }
        private User saveOrUpdate() {
            User user = userRepository.findByEmail(
                    "test@test.test")
                    .map( entity -> entity.update("TEST ACCOUNT", "https://avatars0.githubusercontent.com/u/58850220?s=460&u=d92052902d77e259e1710ab5cc6fac36cd4a093c&v=4") )
                    .orElse(
                        User.builder()
                            .email("test@test.test")
                            .name("TEST ACCOUNT")
                            .picture("https://avatars0.githubusercontent.com/u/58850220?s=460&u=d92052902d77e259e1710ab5cc6fac36cd4a093c&v=4")
                            .role(Role.GUEST)
                            .build()
                    );

            return userRepository.save(user);
        }


}
