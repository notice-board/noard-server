package com.noticeboard.noard.domain.user.repository;

import com.noticeboard.noard.domain.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        if (user.getUserId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }

    public User findOne(long userId) {
        return em.find(User.class, userId);
    }

    public void delete(User user) {
        em.remove(user);
    }

    public Optional<User> findByEmail(String email) {
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("email", email);

        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
