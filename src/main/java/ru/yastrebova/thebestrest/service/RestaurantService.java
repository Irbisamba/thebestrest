package ru.yastrebova.thebestrest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yastrebova.thebestrest.exception.RestaurantNotFoundException;
import ru.yastrebova.thebestrest.model.Meal;
import ru.yastrebova.thebestrest.model.Restaurant;
import ru.yastrebova.thebestrest.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant create(String name, String address, int adminId) {
        Restaurant restaurant = new Restaurant(name, address, adminId, LocalDate.now());
        restaurant = restaurantRepository.save(restaurant);
        log.debug("Restaurant created " + restaurant);
        return restaurant;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }

    public Meal addMeal(Integer restaurantId, String mealTitle, Integer price) {
        Restaurant restaurant = restaurantRepository.findRestaurant(restaurantId);
        if( restaurant == null) {
            throw new RestaurantNotFoundException(String.format("Restaurant with id - %d not found in DataBase", restaurantId));
        }
        restaurant.setMealTitle(mealTitle);
        restaurant.setMealPrice(price);
        restaurantRepository.save(restaurant);
        Meal meal = new Meal(mealTitle, price, restaurantId);
        meal = restaurantRepository.addMeal(meal);
        log.debug("Meal added " + meal);
        return meal;
    }
}
