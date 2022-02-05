package com.security.demo.dashboard.entity;

import com.sun.istack.NotNull;

import javax.annotation.Nullable;
import javax.persistence.*;

@Entity
@Table(name = "TABLE_DASHBOARD_POST")
public class DashBoardPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String writer;

    @NotNull
    private String title;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
