package com.tistory.aristatait.web.controller;

import com.tistory.aristatait.service.posts.PostsService;
import com.tistory.aristatait.web.dto.PostsResponseDto;
import com.tistory.aristatait.web.dto.PostsSaveRequestDto;
import com.tistory.aristatait.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-17
 **/
@RequiredArgsConstructor //final 이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복이 대신 생성해준다
@RequestMapping("/api/v1")
@RestController
public class PostsApiController {

    private final PostsService postsService;

    /**
     * 등록
     */
    @PostMapping("/posts")
    public Long save(
            @RequestBody PostsSaveRequestDto requestDto
    ) {
        return postsService.save(requestDto);
    }

    /**
     * 수정
     */
    @PutMapping("/posts/{id}")
    public Long update(
            @PathVariable Long id,
            @RequestBody PostsUpdateRequestDto requestDto
    ) {
        return postsService.update(id, requestDto);
    }

    /**
     * 조회
     */
    @GetMapping("/posts/{id}")
    public PostsResponseDto findById(
            @PathVariable Long id
    ) {
        return postsService.findById(id);
    }
}
