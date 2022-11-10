package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.PostNotFoundException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final UserService userService;
    private final List<Post> posts = new ArrayList<>();

    private static Integer globalId = 1;

    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Post> findAll(Integer size, String sort, Integer from) {
        return posts.stream()
                .sorted((p1, p2) -> {
                    int comparator = p1.getCreationDate().compareTo(p2.getCreationDate());
                    if (sort.equals("desc")) {
                        comparator = -1 * comparator;
                    }
                    return comparator;
                })
                .skip(from)
                .limit(size)
                .collect(Collectors.toList());
    }

    public static Integer getNextId() {
        return globalId++;
    }

    public Post create(Post post) {
        User author = userService.findUserByEmail(post.getAuthor());
        if (author == null) {
            throw new UserNotFoundException(String.format("Пользователь %s не найден", post.getAuthor()));
        }
        post.setId(getNextId());
        posts.add(post);
        return post;
    }

    public Post findById(Integer postId) {
        return posts.stream()
                .filter(x -> x.getId().equals(postId))
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException(String.format("Пост № %d не найден", postId)));
    }
}
