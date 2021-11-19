package com.tistory.aristatait;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성등등의 기능을 활성화
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // 내장 WAS(Web Application Server)를 실행한다.
        /* 내장 WAS 를 권고 하는 이유
         언제 어디서나 같은 환경에서 스프링 부트를 배포 할 수 있기 때문에
         만약 외장 WAS 를 사용한다면, 서버가 여러개일 경우 버전이 변경되었다면,
         일일이 수정하는 시간도 많이 들고, 실수가 발생할 여지도 있다.*/
    }
}
