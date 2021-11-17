package com.tistory.aristatait.web.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-17
 **/
@Getter
public class PostsUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
