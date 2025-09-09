package com.example.board.post.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PostRequest {
    @NotBlank @Size(max = 200)
    private String title;

    @NotBlank @Size(max = 100)
    private String author;

    @NotBlank
    private String content;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
