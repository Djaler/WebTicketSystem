package com.moracle.webticketsystem.model.repository;

import com.moracle.webticketsystem.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dmitry on 8/4/2016.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
