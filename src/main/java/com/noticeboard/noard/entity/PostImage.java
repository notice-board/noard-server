package com.noticeboard.noard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "postId")
    private Post post;

    private String imageUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostImage createPostImage(String image) {
        PostImage postImage = new PostImage();
        postImage.setImageUrl(image);
        return postImage;
    }
}
