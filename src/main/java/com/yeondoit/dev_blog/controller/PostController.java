package com.yeondoit.dev_blog.controller;

import com.yeondoit.dev_blog.domain.Category;
import com.yeondoit.dev_blog.domain.Post;
import com.yeondoit.dev_blog.dto.PostDTO;
import com.yeondoit.dev_blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public String listPosts(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "post";
    }

    @GetMapping("/new")
    public String createPostForm(Model model) {
        model.addAttribute("post", new PostDTO());
        model.addAttribute("categories", Category.values());
        return "post_form";
    }

    @PostMapping
    public String savePost(@ModelAttribute PostDTO postDTO) {
        if (postDTO.getTags() != null) {
            List<String> tags = Arrays.asList(postDTO.getTags().toString().split(","));
            postDTO.setTags(tags);
        }
        postDTO.setCreatedDate(LocalDateTime.now());
        postDTO.setModifiedDate(LocalDateTime.now());
        Post post = Post.fromDTO(postDTO);
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        PostDTO postDTO = post.toDTO();
        model.addAttribute("post", postDTO);
        return "post_detail";
    }

    @GetMapping("/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        PostDTO postDTO = post.toDTO();
        model.addAttribute("post", postDTO);
        model.addAttribute("categories", Category.values());
        return "post_form";
    }

    @PostMapping("/update")
    public String updatePost(@ModelAttribute PostDTO postDTO) {
        postDTO.setCreatedDate(postService.findById(postDTO.getId()).getCreatedDate());
        postDTO.setModifiedDate(LocalDateTime.now());
        Post post = Post.fromDTO(postDTO);
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deleteById(id);
        return "redirect:/posts";
    }
}
