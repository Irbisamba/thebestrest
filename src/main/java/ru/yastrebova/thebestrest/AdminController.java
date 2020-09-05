package ru.yastrebova.thebestrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.yastrebova.thebestrest.model.Meal;
import ru.yastrebova.thebestrest.model.Restaurant;
import ru.yastrebova.thebestrest.model.request.MealRequest;
import ru.yastrebova.thebestrest.model.request.RestaurantRequest;
import ru.yastrebova.thebestrest.service.RestaurantService;

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

    public Meal addMeal(@RequestBody MealRequest meal) {
        return null;
    }
}
