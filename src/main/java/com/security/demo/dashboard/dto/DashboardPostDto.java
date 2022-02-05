package com.security.demo.dashboard.dto;

import com.security.demo.dashboard.entity.DashboardPost;
import com.sun.istack.NotNull;

public class DashboardPostDto {

    private Long id;
    private String writer;
    private String title;
    private String content;

    public DashboardPostDto(DashboardPost dashboardPost) {
        this.id = dashboardPost.getId();
        this.writer = dashboardPost.getWriter();
        this.title = dashboardPost.getTitle();
        this.content = dashboardPost.getContent();
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
