package com.tistory.aristatait.config.auth;

import com.tistory.aristatait.config.auth.dto.OAuthAttributes;
import com.tistory.aristatait.config.auth.dto.SessionUser;
import com.tistory.aristatait.domain.user.User;
import com.tistory.aristatait.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-19
 **/
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        /* 현재 로그인 진행 중인 서비스를 구분하는 코드
         * 지금은 구글만 사용하기 때문에 불필요해 보이지만,
         * 네이버 로그인이 추가된다면,
         * 네이버 로그인인지, 구글 로그인인지 구분하기 위해 사용된다
         * */
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        /* OAuth2 로그인 진행시 키가 되는 필드값
         * 일종의 Primary key
         * 구글의 경우 기본적으로 코드를 지원하지만, 네이버, 카카오 등은 기본 지원하지 않는다
         * 구글의 기본 코드는 "sub" 이다
         * */
        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        /* OAuth2UserService 를 통해 가져온 OAuth2User 의 attribute 를 담을 DTO 클래스
         * */
        OAuthAttributes attributes = OAuthAttributes.of(
                registrationId,
                userNameAttributeName,
                oAuth2User.getAttributes()
        );

        /* SessionUser
         * 세션에 사용자 정보를 저장하기 위한 DTO 클래스
         * 바로 User 클래스를 사용하지 않고 SessionUser 라는 DTO 클래스를 만든 이유?
         * 세션에 데이터를 저장하기 위해서는 직렬화가 필요한데,
         * 엔티티를 직렬화 하는 것은 확장성을 저해한다
         * 만약 User Entity 가 다른 Entity 와 연관 관계를 맺게 되면 직렬화 과정에서 해당 Entity 까지 포함되기 때문에
         * 성능이슈나 부수 효과 등이 발생할 수 있기 때문이다
         * 그래서 직렬화 기능을 가진 세션 DTO 를 별도로 추가해 사용하는 것이 이후 운영 및 유지보수에 도움이 된다
         * */
        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private User saveOrUpdate(
            OAuthAttributes attributes
    ) {
        String name = attributes.getName();
        String email = attributes.getEmail();
        String picture = attributes.getPicture();

        User user = userRepository.findByEmail(email)
                .map(entity -> entity.update(name, picture))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
