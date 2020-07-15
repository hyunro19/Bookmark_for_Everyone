package com.hyunro.bookmark.web;

import com.hyunro.bookmark.service.comment.CommentService;
import com.hyunro.bookmark.web.dto.comment.CommentSaveRequestDto;
import com.hyunro.bookmark.web.dto.comment.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/v1/comment")
    public Long save(@RequestBody CommentSaveRequestDto requestDto) {
        Long user_id = null;

        return commentService.save(requestDto, user_id);
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
