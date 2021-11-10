package com.tistory.aristatait.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-10
 **/
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
