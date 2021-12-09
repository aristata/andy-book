package com.tistory.aristatait.config.auth.dto;

import com.tistory.aristatait.domain.user.Role;
import com.tistory.aristatait.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-19
 **/
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(
            Map<String, Object> attributes,
            String nameAttributeKey,
            String name,
            String email,
            String picture
    ) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    /**
     * OAuth2User 에서 반환하는 사용자 정보는 Map 이기 때문에 값 하나하나를 변환해야 한다
     */
    public static OAuthAttributes of(
            String registrationId,
            String userNameAttributeName,
            Map<String, Object> attributes
    ) {
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(
            String userNameAttributeName,
            Map<String, Object> attributes
    ) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofGoogle(
            String userNameAttributeName,
            Map<String, Object> attributes
    ) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    /**
     * User Entity 를 생성한다
     * OAuthAttributes 에서 엔티티를 생성하는 시점은 처음 가입할 때이다
     * 가입할 때의 기본 권한을 GUEST 로 주기 위해서 role 빌더값에는 Role.GUEST 를 사용한다
     */
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
