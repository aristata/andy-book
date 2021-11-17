package com.tistory.aristatait.service.posts;

import com.tistory.aristatait.domain.posts.PostsRepository;
import com.tistory.aristatait.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-17
 **/
@RequiredArgsConstructor //final 이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복이 대신 생성해준다
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
