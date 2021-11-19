package com.tistory.aristatait.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-19
 * <p>
 * 1) 이 어노테이션이 생성될 수 있는 위치를 지정한다
 * 2) @interface => 이 파일을 어노테이션 클래스로 지정한다
 **/
@Target(ElementType.PARAMETER) //1
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { //2
}
