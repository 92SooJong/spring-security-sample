package com.security.demo.controller.post;

import com.security.demo.controller.post.dto.PostDto;
import com.security.demo.controller.post.dto.PostSaveRequestDto;
import com.security.demo.controller.user.dto.RegistrationForm;
import com.security.demo.service.post.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/list")
    public String getposts(Model model){
        // service를 통해 model을 모두 불러온다.
        model.addAttribute("posts" , postService.findAllDesc());
        return "/post/postList";
    }

    @GetMapping("/writer")
    public String getPostWriter(Model model){
        model.addAttribute("savePostDto" , new PostSaveRequestDto());

        return "/post/writingPost";
    }

    @PostMapping
    public String addPost(@ModelAttribute PostSaveRequestDto postSaveRequestDto){
        postService.save(postSaveRequestDto);
        return "redirect:/post/list";
    }


}
