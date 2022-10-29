package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.user.User;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Set<User> users = new HashSet<>();

    @GetMapping
    public Set<User> getUsers() {
        return users;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("У нового пользователя не указан email");
        }
        for (User currentUser : users) {
            if (user.equals(currentUser)) {
                throw new UserAlreadyExistException("Пользователь с таким email уже существует");
            }
        }
        users.add(user);
        return user;
    }

    @PutMapping
    public User putUser(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("У пользователя не указан email");
        }
        for (User currentUser : users) {
            if (user.equals(currentUser)) {
                currentUser.setNickname(user.getNickname());
                currentUser.setBirthdate(user.getBirthdate());
                return user;
            }
        }
        users.add(user);
        return user;
    }

}
