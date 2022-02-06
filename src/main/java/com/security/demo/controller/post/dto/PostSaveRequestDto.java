package com.security.demo.controller.post.dto;

import com.security.demo.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostSaveRequestDto {

    private String writer;
    private String title;
    private String content;

    public Post toEntity(){
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .build();
    }


}
