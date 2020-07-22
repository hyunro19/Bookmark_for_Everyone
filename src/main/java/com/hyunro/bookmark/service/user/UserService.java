package com.hyunro.bookmark.service.user;

import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.domain.user.UserRepository;
import com.hyunro.bookmark.web.dto.user.UserResponseDto;
import com.hyunro.bookmark.web.dto.user.UserSaveRequestDto;
import com.hyunro.bookmark.web.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User save(UserSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity());
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        user.update(requestDto);
        return id;
    }

    public UserResponseDto findById(Long id) {
        User entity = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("해당 사용자가 없습니다. id="+id)));
        return new UserResponseDto(entity);
    }

    public User findByEmail(String email) {
        User entity = userRepository.findByEmail(email);
//        if(entity==null) throw new IllegalArgumentException(("해당 사용자가 없습니다. email="+email));
        return entity;
    }

    public User findByName(String name) {
        User entity = userRepository.findByName(name);
//        if(entity==null) throw new IllegalArgumentException(("해당 사용자가 없습니다. email="+name));
        return entity;
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        userRepository.delete(user);
    }
}
