package ru.yastrebova.thebestrest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yastrebova.thebestrest.model.Meal;
import ru.yastrebova.thebestrest.model.Restaurant;

import java.util.List;

@Repository
public class RestaurantRepository {

    CrudRestaurantRepository crudRestaurantRepository;

    CrudMealRepository crudMealRepository;

    @Autowired
    public RestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    public Restaurant create(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return crudRestaurantRepository.findAll();
    }

    public Restaurant findRestaurant(Integer restaurantId) {
        return crudRestaurantRepository.findById(restaurantId).orElse(null);
    }

    public Meal addMeal(Meal meal) {
        return crudMealRepository.save(meal);
    }
}
