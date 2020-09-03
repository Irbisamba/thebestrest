package ru.yastrebova.thebestrest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yastrebova.thebestrest.model.Restaurant;
import ru.yastrebova.thebestrest.model.User;

@Repository
public class RestaurantRepository {

    CrudRestaurantRepository crudRestaurantRepository;

    @Autowired
    public RestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    public Restaurant create(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }
}
