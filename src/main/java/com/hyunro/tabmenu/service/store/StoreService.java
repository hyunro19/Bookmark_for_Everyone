package com.hyunro.tabmenu.service.store;

import com.hyunro.tabmenu.domain.posts.Posts;
import com.hyunro.tabmenu.domain.posts.PostsRepository;
import com.hyunro.tabmenu.domain.store.Store;
import com.hyunro.tabmenu.domain.store.StoreRepository;
import com.hyunro.tabmenu.web.dto.posts.PostsListResponseDto;
import com.hyunro.tabmenu.web.dto.posts.PostsResponseDto;
import com.hyunro.tabmenu.web.dto.posts.PostsSaveRequestDto;
import com.hyunro.tabmenu.web.dto.posts.PostsUpdateRequestDto;
import com.hyunro.tabmenu.web.dto.store.StoreListResponseDto;
import com.hyunro.tabmenu.web.dto.store.StoreResponseDto;
import com.hyunro.tabmenu.web.dto.store.StoreSaveRequestDto;
import com.hyunro.tabmenu.web.dto.store.StoreUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Transactional
    public Long save(StoreSaveRequestDto requestDto) {
        return storeRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, StoreUpdateRequestDto requestDto) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        store.update(requestDto.getName(), requestDto.getPhone(), requestDto.getAddress(), requestDto.getType());
        return id;
    }

    public StoreResponseDto findById(Long id) {
        Store entity = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("해당 사용자가 없습니다. id="+id)));
        return new StoreResponseDto(entity);
    }

    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되, 조회 기능만 남겨두오 조회 속도가 개선
    public List<StoreListResponseDto> findAllAsc() {
        return storeRepository.findAllAsc().stream()
                .map(StoreListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        storeRepository.delete(store);
    }
}
