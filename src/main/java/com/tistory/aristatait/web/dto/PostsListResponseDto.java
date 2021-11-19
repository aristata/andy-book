package com.tistory.aristatait.web.dto;

import com.tistory.aristatait.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-19
 **/
@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
