package ru.yastrebova.thebestrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.yastrebova.thebestrest.model.Meal;
import ru.yastrebova.thebestrest.model.Restaurant;
import ru.yastrebova.thebestrest.model.request.MealListRequest;
import ru.yastrebova.thebestrest.model.request.MealRequest;
import ru.yastrebova.thebestrest.model.request.RestaurantRequest;
import ru.yastrebova.thebestrest.service.RestaurantService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@ResponseBody
public class AdminController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping(value = "/restaurant/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantRequest restaurant) {
        Restaurant result = restaurantService.create(restaurant.getName(), restaurant.getAddress(), restaurant.getAdminId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/restaurant/newMealList", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Meal>> newMealList(@RequestBody MealListRequest meal) {
        List<Meal> result = restaurantService.newMealList(meal.getRestaurantId(), meal.getMeals());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/restaurant/addMeal", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Meal> addMeal(@RequestBody MealRequest meal) {
        Meal result = restaurantService.addMeal(meal.getRestaurantId(), meal.getMealTitle(), meal.getPrice());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get-restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    @GetMapping("/{id}/get-restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(@PathVariable("id") Integer adminId) {
        return new ResponseEntity<>(restaurantService.getRestaurantsForAdmin(adminId), HttpStatus.OK);
    }

    @GetMapping("/restaurant/delete")
    public ResponseEntity<String> deleteRestaurant(@RequestParam Integer restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<>("Restaurant with id " + restaurantId + " successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/restaurant/clearMeals")
    public ResponseEntity<String> clearMeals(@RequestParam Integer restaurantId) {
        restaurantService.clearMeals(restaurantId);
        return new ResponseEntity<>("Meals cleared for restaurant with id " + restaurantId, HttpStatus.OK);
    }
}
