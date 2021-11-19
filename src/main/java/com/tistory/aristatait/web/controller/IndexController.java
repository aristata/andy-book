package com.tistory.aristatait.web.controller;

import com.tistory.aristatait.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-19
 **/
@RequiredArgsConstructor //final 로 선언된 필드들을 포함한 생성자를 자동으로 만들어 준다
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
