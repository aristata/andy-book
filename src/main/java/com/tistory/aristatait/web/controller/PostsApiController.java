package com.tistory.aristatait.web.controller;

import com.tistory.aristatait.service.posts.PostsService;
import com.tistory.aristatait.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-17
 **/
@RequiredArgsConstructor //final 이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복이 대신 생성해준다
@RequestMapping("/api/v1")
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/posts")
    public Long save(
            @RequestBody PostsSaveRequestDto requestDto
    ) {
        return postsService.save(requestDto);
    }
}
