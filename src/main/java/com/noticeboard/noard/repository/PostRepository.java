package com.noticeboard.noard.repository;

import com.noticeboard.noard.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
public class PostRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Post post) {
        if (post.getPostId() == null) {
            em.persist(post); // 애초에 반환 타입이 없음.
        } else {
            em.merge(post);
        }
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class).getResultList();
    }

    public Post findOne(long postId) {
        return em.find(Post.class, postId);
    }

    public void delete(Post post) {
        em.remove(post);
    }
}
