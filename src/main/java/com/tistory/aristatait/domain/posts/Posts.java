package com.tistory.aristatait.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-10
 **/
@Getter
@NoArgsConstructor //기본 생성자 자동 추가
@Entity //테이블과 링크될 클래스임을 나타낸다. 스네이크 케이스로 매핑된다
public class Posts {

    @Id //해당 테이블의 PK 필드를 나타낸다
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성 규칙이다. IDENTITY 옵션을 사용하면 auto_increment 적용
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성한다. 생성자에 선언시 생성자에 포함된 필드만 빌더에 포함된다
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(
            String title,
            String content
    ) {
        this.title = title;
        this.content = content
    }
}
