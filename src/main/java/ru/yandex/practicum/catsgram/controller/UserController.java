package ru.yandex.practicum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.service.UserService;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService= userService;
    }

    @GetMapping
    public Collection<User> getUsers() {
        log.trace("Количество пользователей: {}", userService.findAll().size());
        return userService.findAll();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        log.trace("Новый пользователь: {}", user);
        return userService.addUser(user);
    }

    @PutMapping
    public User putUser(@RequestBody User user) {
        log.trace("Обновленный пользователь: {}", user);
        return userService.updateUser(user);
    }

}
