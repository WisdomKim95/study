package com.example.board.post.dto;

import com.example.board.post.Post;
import java.time.format.DateTimeFormatter;

public class PostResponse {
    private final Long id;
    private final String title;
    private final String author;
    private final String content;
    private final String createdAt;
    private final String updatedAt;

    public PostResponse(Post p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.author = p.getAuthor();
        this.content = p.getContent();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createdAt = p.getCreatedAt() == null ? "" : p.getCreatedAt().format(f);
        this.updatedAt = p.getUpdatedAt() == null ? "" : p.getUpdatedAt().format(f);
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getContent() { return content; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
}
