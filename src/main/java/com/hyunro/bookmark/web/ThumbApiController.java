package com.hyunro.bookmark.web;

import com.hyunro.bookmark.config.auth.LoginUser;
import com.hyunro.bookmark.config.auth.dto.SessionUser;
import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.bookmark.BookmarkRepository;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.domain.user.UserRepository;
import com.hyunro.bookmark.service.thumb.ThumbService;
import com.hyunro.bookmark.web.dto.thumb.ThumbSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ThumbApiController {


    private final ThumbService thumbService;

    @PostMapping("/api/v1/thumb")
    public Long save(@RequestBody ThumbSaveRequestDto requestDto, @LoginUser SessionUser sessionUser) {
        Long user_id = null;

        if(sessionUser != null) {
            user_id = sessionUser.getId();
        }

        return thumbService.save(requestDto, user_id);
    }

    @DeleteMapping("api/v1/thumb/{id}")
    public Long delete (@PathVariable Long id) {
        thumbService.delete(id);
        return id;
    }
}
