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

    @GetMapping("/post/{postId}")
    public ResponseEntity<Object> getPost(@PathVariable("postId") long postId) {
        Post foundPost = postService.findOne(postId);

        if (foundPost == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 게시물이 존재하지 않습니다.");
        }

//        List<String> imageUrl = foundPost.getPostImages();
//        Map<String, Object> response = new HashMap<>();
//        response.put("post", foundPost);
//        response.put("imageUrl", imageUrl);

        return ResponseEntity.status(HttpStatus.OK).body(foundPost);
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