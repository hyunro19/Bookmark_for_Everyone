package com.hyunro.bookmark.service.bookmark;

import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.bookmark.BookmarkRepository;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.web.dto.bookmark.BookmarkListResponseDto;
import com.hyunro.bookmark.web.dto.bookmark.BookmarkResponseDto;
import com.hyunro.bookmark.web.dto.bookmark.BookmarkSaveRequestDto;
import com.hyunro.bookmark.web.dto.bookmark.BookmarkUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public Long save(BookmarkSaveRequestDto requestDto, User user) {
        return bookmarkRepository.save(requestDto.toEntity(user)).getId();
    }

    @Transactional
    public Long update(Long id, BookmarkUpdateRequestDto requestDto) {
        Bookmark bookmark = bookmarkRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        bookmark.update(requestDto.getTopic(), requestDto.getUrl(), requestDto.getSrc_title(), requestDto.getSrc_description(), requestDto.getContent(),
                requestDto.getNumber_comment(), requestDto.getNumber_thumb(), requestDto.getNumber_share(), requestDto.getIs_public());
        return id;
    }

    public BookmarkResponseDto findById(Long id) {
        Bookmark entity = bookmarkRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("해당 사용자가 없습니다. id="+id)));
        return new BookmarkResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BookmarkListResponseDto> findAllDesc() {
        return bookmarkRepository.findAllDesc().stream()
                .map(BookmarkListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Bookmark Bookmark = bookmarkRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시그링 없습니다. id=" + id));
        bookmarkRepository.delete(Bookmark);
    }
}
