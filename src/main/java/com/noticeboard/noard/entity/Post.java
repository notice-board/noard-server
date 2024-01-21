package com.noticeboard.noard.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.noticeboard.noard.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String title;

    private String content;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<PostImage> postImages = new ArrayList<>(); // post 여러 장

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static Post createPost(String title, String content, List<String> imageUrls) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        for (String imageUrl : imageUrls) {
            post.addPostImage(imageUrl);
        }
        return post;
    }

    public void addPostImage(String imagUrl) {
        PostImage postImage = PostImage.createPostImage(imagUrl);
        postImages.add(postImage);
        postImage.setPost(this);
    }
}
