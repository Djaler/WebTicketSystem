package com.moracle.webticketsystem.model.service.impl;

import com.moracle.webticketsystem.model.entity.Role;
import com.moracle.webticketsystem.model.entity.User;
import com.moracle.webticketsystem.model.exception.UserAlreadyExists;
import com.moracle.webticketsystem.model.repository.UserRepository;
import com.moracle.webticketsystem.model.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dmitry on 8/4/2016.
 */
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static String hashPassword(String passwordPlaintext) {
        return BCrypt.hashpw(passwordPlaintext, BCrypt.gensalt());
    }

    @Override
    public User registrationNewUser(String login, String pass, String name, Role role) throws UserAlreadyExists {
        User user = new User(login, hashPassword(pass), name, role);
        return registrationNewUser(user);
    }

    @Override
    public User registrationNewUser(User user) throws UserAlreadyExists {
        if (getByLogin(user.getLogin()) == null) {
            return userRepository.saveAndFlush(user);
        } else {
            throw new UserAlreadyExists();
        }
    }

    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

}
