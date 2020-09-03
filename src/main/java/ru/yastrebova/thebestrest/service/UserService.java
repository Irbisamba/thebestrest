package ru.yastrebova.thebestrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yastrebova.thebestrest.model.User;
import ru.yastrebova.thebestrest.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User save(String name, String email, String password) {
        User user = new User(name, email, password);
        user = repository.save(user);
        System.out.println(user);
        return user;
    }
}
