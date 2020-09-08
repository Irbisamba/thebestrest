package ru.yastrebova.thebestrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yastrebova.thebestrest.model.Restaurant;
import ru.yastrebova.thebestrest.model.User;
import ru.yastrebova.thebestrest.repository.RestaurantRepository;
import ru.yastrebova.thebestrest.repository.UserRepository;
import ru.yastrebova.thebestrest.model.response.RestaurantMeal;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public User save(String name, String email, String password) {
        User user = new User(name, email, password);
        user = repository.save(user);
        System.out.println(user);
        return user;
    }

    public void vote(Integer userId, Integer restaurantId) {
        Restaurant restaurant = restaurantRepository.findRestaurant(restaurantId);
        restaurant.setRating(restaurant.getRating() + 1);
        restaurantRepository.save(restaurant);
    }

    public List<RestaurantMeal> getRestaurantMealList() {
        return restaurantRepository.getAllRestaurants().stream().map(m ->
                new RestaurantMeal()
                        .setRestaurantId(m.getId())
                        .setRestaurantName(m.getName())
                        .setMealTitle(m.getMealTitle())
                        .setPrice(m.getMealPrice())).collect(Collectors.toList());
    }
}
