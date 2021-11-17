package com.tistory.aristatait.web.dto;

import com.tistory.aristatait.domain.posts.Posts;
import lombok.Getter;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-17
 **/
@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
