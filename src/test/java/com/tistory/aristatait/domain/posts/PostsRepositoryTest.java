package com.tistory.aristatait.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-10
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After //JUnit 에서 단위 테스트가 끝날 때마다 수행되는 메소드
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        Posts post = Posts.builder()
                .title(title)
                .content(content)
                .author("aristatait@gmail.com")
                .build();

        postsRepository.save(post); //테이블에 insert/update 쿼리를 실행한다. id 값이 있으면 update, 없으면 insert 쿼리가 실행된다.

        //when
        List<Posts> postsList = postsRepository.findAll(); //테이블에서 모든 데이터를 조회한다.

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
