package com.hyunro.bookmark.web;

import com.hyunro.bookmark.service.thumb.ThumbService;
import com.hyunro.bookmark.web.dto.thumb.ThumbSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ThumbApiController {


    private final ThumbService thumbService;

    @PostMapping("/api/v1/thumb")
    public Long save(@RequestBody ThumbSaveRequestDto requestDto) {
        Long user_id = null;

        return thumbService.save(requestDto, user_id);
    }

    @DeleteMapping("api/v1/thumb/{id}")
    public Long delete (@PathVariable Long id) {
        thumbService.delete(id);
        return id;
    }
}
