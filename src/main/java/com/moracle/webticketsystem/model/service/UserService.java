package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.Role;
import com.moracle.webticketsystem.model.entity.User;
import com.moracle.webticketsystem.model.exception.UserAlreadyExists;

import java.util.List;

/**
 * Created by dmitry on 8/4/2016.
 */
public interface UserService {
    User registrationNewUser(String login, String pass, String name, Role role) throws UserAlreadyExists;
    User registrationNewUser(User user) throws UserAlreadyExists;
    User getByLogin(String login);

    List<User> getUsersNotInList(List<User> users);
    List<User> getAll();
    User update(User user);
}
