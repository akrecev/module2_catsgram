package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final UserService userService;
    private final List<Post> posts = new ArrayList<>();

    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post create(Post post) {
        User author = userService.findUserByEmail(post.getAuthor());
        if (author == null) {
            throw new UserNotFoundException(String.format("Пользователь %s не найден", post.getAuthor()));
        }
        posts.add(post);
        return post;
    }

}
