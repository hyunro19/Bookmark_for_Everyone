package com.hyunro.bookmark.web;

import com.hyunro.bookmark.config.auth.LoginUser;
import com.hyunro.bookmark.config.auth.dto.SessionUser;
import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.bookmark.BookmarkRepository;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.domain.user.UserRepository;
import com.hyunro.bookmark.service.comment.CommentService;
import com.hyunro.bookmark.web.dto.comment.CommentSaveRequestDto;
import com.hyunro.bookmark.web.dto.comment.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;
    private final CommentService commentService;

    @PostMapping("/api/v1/comment")
    public Long save(@RequestBody CommentSaveRequestDto requestDto, @LoginUser SessionUser sessionUser) {
        Long user_id = null;
        User user = null;
        Bookmark bookmark = null;
        if(sessionUser != null) {
            user_id = sessionUser.getId();
            user = userRepository.getOne(user_id);
            System.out.println(">>>>>>>>user : "+user_id+"<<<<<<<<<<<<<");
        }
        bookmark = bookmarkRepository.getOne(requestDto.getBookmark_id());
        return commentService.save(requestDto, user, bookmark);
    }

    @PutMapping("/api/v1/comment/{id}")
    public Long update(@PathVariable Long id, @RequestBody CommentUpdateRequestDto requestDto) {
        return commentService.update(id, requestDto);
    }

    @DeleteMapping("api/v1/comment/{id}")
    public Long delete (@PathVariable Long id) {
        commentService.delete(id);
        return id;
    }
}