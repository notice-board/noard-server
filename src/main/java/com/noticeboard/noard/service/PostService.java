package com.noticeboard.noard.service;

import com.noticeboard.noard.dto.AddPostRequestDto;
import com.noticeboard.noard.dto.AddPostResponseDto;
import com.noticeboard.noard.entity.Post;
import com.noticeboard.noard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 작성
    @Transactional(readOnly = false)
    public Post savePost(AddPostRequestDto addPostRequestDto) {
        Post post = Post.createPost(addPostRequestDto.getTitle(), addPostRequestDto.getContent(), addPostRequestDto.getImageUrl());
        postRepository.save(post);
        return post;
    }

    // 읽기
    @Transactional(readOnly = true)
    public List<Post> getPost() {
        return postRepository.findAll();
    }

    public Post findOne(Long postId) {
        return postRepository.findOne(postId);
    }

    // @Transactional(readOnly = false)
    // 수정

    // @Transactional(readOnly = false)
    // 삭제
}
