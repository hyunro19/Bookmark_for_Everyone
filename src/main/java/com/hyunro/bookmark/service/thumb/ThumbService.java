package com.hyunro.bookmark.service.thumb;

import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.thumb.Thumb;
import com.hyunro.bookmark.domain.thumb.ThumbRepository;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.web.dto.thumb.ThumbSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ThumbService {
    private final ThumbRepository thumbRepository;

    @Transactional
    public Long save(ThumbSaveRequestDto requestDto, User user, Bookmark bookmark) {
        return thumbRepository.save(requestDto.toEntity(user, bookmark)).getId();
    }

//    나중에 내가 좋아요한 게시물 보기 기능 만들때 사용
//    @Transactional(readOnly = true)
//    public List<ThumbListResponseDto> findAll() {
//        return thumbRepository.findAll().stream()
//                .map(ThumbListResponseDto::new)
//                .collect(Collectors.toList());
//    }

    @Transactional
    public void delete(Long id) {
        Thumb thumb = thumbRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        thumbRepository.delete(thumb);
    }
}