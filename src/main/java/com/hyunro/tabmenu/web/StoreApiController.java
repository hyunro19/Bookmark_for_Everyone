package com.hyunro.tabmenu.web;

import com.hyunro.tabmenu.service.store.StoreService;
import com.hyunro.tabmenu.web.dto.store.StoreResponseDto;
import com.hyunro.tabmenu.web.dto.store.StoreSaveRequestDto;
import com.hyunro.tabmenu.web.dto.store.StoreUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class StoreApiController {

    // @Autowired대신 @RequiredArgsConstructor가 생성자 주입 방식으로 Bean객체를 연결해준다.
    // final이 선언도니 모든 필드를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConstructor가 대신 생성해 줌
    private final StoreService storeService;

    @PostMapping("/api/v1/store")
    public Long save(@RequestBody StoreSaveRequestDto requestDto) {
        return storeService.save(requestDto);
    }

    @PutMapping("/api/v1/store/{id}")
    public Long update(@PathVariable Long id, @RequestBody StoreUpdateRequestDto requestDto) {
        return storeService.update(id, requestDto);
    }

    @GetMapping("api/v1/store/{id}")
    public StoreResponseDto findById (@PathVariable Long id) {
        return storeService.findById(id);
    }

    @DeleteMapping("api/v1/store/{id}")
    public Long delete (@PathVariable Long id) {
        storeService.delete(id);
        return id;
    }
}
