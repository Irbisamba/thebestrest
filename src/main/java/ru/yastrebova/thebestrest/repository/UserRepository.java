package ru.yastrebova.thebestrest.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yastrebova.thebestrest.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public User save(User user) {
            return em.merge(user);

    }



}
