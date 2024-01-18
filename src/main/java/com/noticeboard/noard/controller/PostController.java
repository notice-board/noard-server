package com.noticeboard.noard.controller;

import com.noticeboard.noard.dto.AddPostRequestDto;
import com.noticeboard.noard.entity.Post;
import com.noticeboard.noard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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

    @GetMapping("/post")
    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> posts = postService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<Post> putPost(@PathVariable("postId") long postId, @RequestBody AddPostRequestDto updatePostDto) {
        Post updatedPost = postService.updatePost(postId, updatePostDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") long postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).body("삭제가 완료되었습니다.");
    }
}