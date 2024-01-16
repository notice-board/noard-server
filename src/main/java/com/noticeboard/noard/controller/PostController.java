package com.noticeboard.noard.controller;

import com.noticeboard.noard.dto.AddPostRequestDto;
import com.noticeboard.noard.entity.Post;
import com.noticeboard.noard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<Post> addPost(@RequestBody AddPostRequestDto addPostRequestDto) {
        Post savedPost = postService.savePost(addPostRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @GetMapping("/find/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable("postId") long postId) {
        Post foundPost = postService.findOne(postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(foundPost);
    }
}
