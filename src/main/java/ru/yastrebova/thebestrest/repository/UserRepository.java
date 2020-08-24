package ru.yastrebova.thebestrest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yastrebova.thebestrest.model.User;

@Repository
public class UserRepository {

    CrudUserRepository crudUserRepository;

    @Autowired
    public UserRepository(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    @Transactional
    public User save(User user) {
            return crudUserRepository.save(user);
    }



}
