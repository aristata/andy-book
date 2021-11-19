package com.tistory.aristatait.config.auth.dto;

import com.tistory.aristatait.domain.user.User;
import lombok.Getter;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-19
 **/
@Getter
public class SessionUser {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
