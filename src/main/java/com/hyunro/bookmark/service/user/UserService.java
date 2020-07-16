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
    public Long save(UserSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        user.update(requestDto.getName(), requestDto.getPassword());
        return id;
    }

    public UserResponseDto findById(Long id) {
        User entity = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("해당 사용자가 없습니다. id="+id)));
        return new UserResponseDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        userRepository.delete(user);
    }
}
