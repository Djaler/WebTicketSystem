package com.moracle.webticketsystem.model.repository;

import com.moracle.webticketsystem.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by dmitry on 8/4/2016.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);

    @Query("select u from User u where u not in :users order by u.name asc")
    List<User> getUserNotInList(@Param(value = "users") List<User> users);
}
