package com.security.demo.controller.post.dto;

import com.security.demo.domain.post.Post;

public class PostDto {

    private Long id;
    private String writer;
    private String title;
    private String content;

    public PostDto(Post post) {
        this.id = post.getId();
        this.writer = post.getWriter();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    public Long getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
