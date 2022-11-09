package ru.yandex.practicum.catsgram.model;

import java.time.Instant;
import java.util.Objects;

public class Post {

    private Integer id;
    private final String author; // автор
    private final Instant creationDate = Instant.now(); // дата создания
    private String description; // описание
    private String photoUrl; // url-адрес фотографии

    public Post(Integer id, String author, String description, String photoUrl) {
        this.id = id;
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id) && author.equals(post.author) && creationDate.equals(post.creationDate) && description.equals(post.description) && photoUrl.equals(post.photoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, creationDate, description, photoUrl);
    }
}