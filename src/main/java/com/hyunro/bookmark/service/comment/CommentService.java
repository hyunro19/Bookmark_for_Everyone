package com.hyunro.bookmark.service.comment;

import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.comment.Comment;
import com.hyunro.bookmark.domain.comment.CommentRepository;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.web.dto.comment.CommentListResponseDto;
import com.hyunro.bookmark.web.dto.comment.CommentSaveRequestDto;
import com.hyunro.bookmark.web.dto.comment.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Long save(CommentSaveRequestDto requestDto, User user, Bookmark bookmark) {
        return commentRepository.save(requestDto.toEntity(user, bookmark)).getId();
    }

    @Transactional
    public Long update(Long id, CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        comment.update(requestDto.getContent());
        return id;
    }

    @Transactional(readOnly = true)
    public List<CommentListResponseDto> findAll() {
        return commentRepository.findAll().stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시그링 없습니다. id=" + id));
        commentRepository.delete(comment);
    }
}
