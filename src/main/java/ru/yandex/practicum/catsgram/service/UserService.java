package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.*;

@Service
public class UserService {

    private final Map<String, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }

    public User addUser(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("У пользователя не указан email");
        }
            if (users.containsKey(user.getEmail())) {
                throw new UserAlreadyExistException(String.format("Пользователь с email: %s уже существует",
                        user.getEmail()));
        }
        users.put(user.getEmail(), user);
        return user;
    }

    public User updateUser(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("У пользователя не указан email");
        }
        users.put(user.getEmail(), user);
        return user;
    }

    public User findUserByEmail(String email) {
         if (email == null) {
             return null;
         }
         return users.get(email);
    }

}
