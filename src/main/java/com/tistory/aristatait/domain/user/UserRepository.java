package com.tistory.aristatait.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-19
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
