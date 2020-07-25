package com.hyunro.bookmark.service.posts;

import com.hyunro.bookmark.domain.posts.Posts;
import com.hyunro.bookmark.domain.posts.PostsRepository;
import com.hyunro.bookmark.domain.user.User;
import com.hyunro.bookmark.domain.user.UserRepository;
import com.hyunro.bookmark.web.dto.posts.PostsListResponseDto;
import com.hyunro.bookmark.web.dto.posts.PostsResponseDto;
import com.hyunro.bookmark.web.dto.posts.PostsSaveRequestDto;
import com.hyunro.bookmark.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUser_id()).get();
        Posts posts = requestDto.toEntity(user);
        return postsRepository.save(posts).getPosts_id();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        posts.update(requestDto.getTopic(), requestDto.getSrc_url(), requestDto.getSrc_title(), requestDto.getSrc_description());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("해당 사용자가 없습니다. id="+id)));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되, 조회 기능만 남겨두오 조회 속도가 개선
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시그링 없습니다. id=" + id));
        postsRepository.delete(posts);
    }
}
