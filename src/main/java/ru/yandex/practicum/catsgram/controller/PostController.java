package ru.yandex.practicum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;


@RestController
@Slf4j
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/posts")
    public List<Post> findAll(
            @RequestParam(defaultValue = "asc") String sort, // по умолчанию прямая сортировка, обратная - desc
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        if (!(sort.equals("asc") || sort.equals("desc"))) {
            throw new IllegalArgumentException("Параметр сортировки должен быть: asc или desc");
        }
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException();
        }
        Integer from = page * size;
        log.debug("Текущее количество постов: {}", postService.getPosts().size());
        return postService.findAll(size, sort, from);
    }

    @PostMapping(value = "/post")
    public Post create(@RequestBody Post post) {
        Post createdPost = postService.create(post);
        log.trace("Новый пост: {}", createdPost);
        return createdPost;
    }

    @GetMapping("/posts/{postId}")
    public Post findById(@PathVariable Integer postId) {
        return postService.findById(postId);
    }
}