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
    public List<Post> findAll() {
        log.debug("Текущее количество постов: {}", postService.findAll().size());
        return postService.findAll();
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