package com.tistory.aristatait.web.controller;

import com.tistory.aristatait.config.auth.LoginUser;
import com.tistory.aristatait.config.auth.dto.SessionUser;
import com.tistory.aristatait.service.posts.PostsService;
import com.tistory.aristatait.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-19
 **/
@RequiredArgsConstructor //final 로 선언된 필드들을 포함한 생성자를 자동으로 만들어 준다
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(
            Model model,
            @LoginUser SessionUser user
    ) {
        model.addAttribute("posts", postsService.findAllDesc());

//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(
            @PathVariable Long id,
            Model model
    ) {
        PostsResponseDto responseDto = postsService.findById(id);
        model.addAttribute("post", responseDto);

        return "posts-update";
    }
}
