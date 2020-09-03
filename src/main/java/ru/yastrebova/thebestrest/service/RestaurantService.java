package ru.yastrebova.thebestrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yastrebova.thebestrest.model.Restaurant;
import ru.yastrebova.thebestrest.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant create(String name, String address, int adminId) {
        Restaurant restaurant = new Restaurant(name, address, adminId, LocalDate.now());
        restaurant = restaurantRepository.create(restaurant);
        System.out.println(restaurant);
        return restaurant;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }
}
