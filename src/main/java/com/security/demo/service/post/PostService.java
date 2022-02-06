package com.security.demo.service.post;

import com.security.demo.controller.post.dto.PostDto;
import com.security.demo.controller.post.dto.PostSaveRequestDto;
import com.security.demo.domain.post.PostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public List<PostDto> findAllDesc(){
        return postRepository.findAllDesc().stream()
                .map(PostDto::new)
                .collect(Collectors.toList());
    }


    public void save(PostSaveRequestDto postSaveRequestDto) {
        postRepository.save(postSaveRequestDto.toEntity());
    }
}
