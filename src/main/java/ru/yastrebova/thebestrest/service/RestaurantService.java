package ru.yastrebova.thebestrest.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yastrebova.thebestrest.exception.RestaurantNotFoundException;
import ru.yastrebova.thebestrest.model.Meal;
import ru.yastrebova.thebestrest.model.Restaurant;
import ru.yastrebova.thebestrest.model.request.MealListRequest;
import ru.yastrebova.thebestrest.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Data
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant create(String name, String address, int adminId) {
        Restaurant restaurant = new Restaurant(name, address, adminId);
        restaurant.setDateOfLastUpdating(LocalDate.now());
        restaurant = restaurantRepository.save(restaurant);
        log.debug("Restaurant created " + restaurant);
        return restaurant;
    }

    public void deleteRestaurant(Integer restaurantId) {
        restaurantRepository.delete(restaurantId);
    }

    public void clearMeals(Integer restaurantId) {
        Restaurant restaurant = restaurantRepository.findRestaurant(restaurantId);
        if (restaurant==null) {
            throw new RestaurantNotFoundException(String.format("Restaurant with id - %d not found in DataBase", restaurantId));
        }
        restaurant.setMeals(new HashMap<>());
        restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }

    public List<Restaurant> getRestaurantsForAdmin(Integer adminId) {
        return restaurantRepository.getAllRestaurantsForAdmin(adminId);
    }

    public List<Meal> newMealList(Integer restaurantId, List<MealListRequest.Meal> meals) {
        Restaurant restaurant = restaurantRepository.findRestaurant(restaurantId);
        if( restaurant == null) {
            throw new RestaurantNotFoundException(String.format("Restaurant with id - %d not found in DataBase", restaurantId));
        }
        List<Meal> mealsToDB = new ArrayList<>();
        Map<String,Integer> restaurantMeal = new HashMap<>();
        for(MealListRequest.Meal m : meals) {
            String mealTitle = m.getMealTitle();
            Integer price = m.getPrice();
            Meal meal = new Meal(mealTitle, price, restaurantId);
            meal = restaurantRepository.addMeal(meal);
            mealsToDB.add(meal);
            restaurantMeal.put(mealTitle, price);
            log.debug("Meal added " + meal);
        }
        restaurant.setMeals(restaurantMeal);
        restaurant.setDateOfLastUpdating(LocalDate.now());
        restaurantRepository.save(restaurant);
        log.debug("List of meals added");
        return mealsToDB;
    }

    public Meal addMeal(Integer restaurantId, String mealTitle, Integer price) {
        Restaurant restaurant = restaurantRepository.findRestaurant(restaurantId);
        if( restaurant == null) {
            throw new RestaurantNotFoundException(String.format("Restaurant with id - %d not found in DataBase", restaurantId));
        }
        if (restaurant.getMeals()==null) {
            restaurant.setMeals(new HashMap<>());
            restaurant.getMeals().put(mealTitle, price);
            restaurant.setDateOfLastUpdating(LocalDate.now());
            log.debug("List of meals created. List " + restaurant.getMeals());
        } else {
            if(restaurant.getMeals().isEmpty()) {
                restaurant.setDateOfLastUpdating(LocalDate.now());
                restaurant.getMeals().put(mealTitle, price);
                log.debug("Meal added to list " + mealTitle + " : " + price);
            } else if (!restaurant.getDateOfLastUpdating().equals(LocalDate.now())) {
                //если список еды создан не сегодня, то обновляем полностью
                Map<String, Integer> meals = new HashMap<>();
                meals.put(mealTitle, price);
                restaurant.setMeals(meals);
                restaurant.setDateOfLastUpdating(LocalDate.now());
                log.debug("List of meals updated. List " + restaurant.getMeals());
            } else {
                //если список свежий, то дату обновления оставляем как есть
                restaurant.getMeals().put(mealTitle, price);
                log.debug("Meal added to list " + mealTitle + " : " + price);
            }

        }
        restaurantRepository.save(restaurant);
        Meal meal = new Meal(mealTitle, price, restaurantId);
        meal = restaurantRepository.addMeal(meal);
        log.debug("Meal added " + meal);
        return meal;
    }
}
