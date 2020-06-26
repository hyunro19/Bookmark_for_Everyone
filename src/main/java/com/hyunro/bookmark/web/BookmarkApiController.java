package com.hyunro.bookmark.web;

import com.hyunro.bookmark.config.auth.LoginUser;
import com.hyunro.bookmark.config.auth.dto.SessionUser;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.domain.user.UserRepository;
import com.hyunro.bookmark.service.bookmark.BookmarkService;
import com.hyunro.bookmark.web.dto.bookmark.BookmarkResponseDto;
import com.hyunro.bookmark.web.dto.bookmark.BookmarkSaveRequestDto;
import com.hyunro.bookmark.web.dto.bookmark.BookmarkUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class BookmarkApiController {

    private final UserRepository userRepository;
    private final BookmarkService bookmarkService;

    @PostMapping("/api/v1/bookmark")
    public Long save(@RequestBody BookmarkSaveRequestDto requestDto, @LoginUser SessionUser sessionUser) {
        Long user_id;
        User user = null;
        if(sessionUser != null) {
            user_id = sessionUser.getId();
            user = userRepository.getOne(user_id);
        }
        return bookmarkService.save(requestDto, user);
    }

    @PutMapping("/api/v1/bookmark/{id}")
    public Long update(@PathVariable Long id, @RequestBody BookmarkUpdateRequestDto requestDto) {
        return bookmarkService.update(id, requestDto);
    }

    @GetMapping("api/v1/bookmark/{id}")
    public BookmarkResponseDto findById (@PathVariable Long id) {
        return bookmarkService.findById(id);
    }

    @DeleteMapping("api/v1/bookmark/{id}")
    public Long delete (@PathVariable Long id) {
        bookmarkService.delete(id);
        return id;
    }
}
