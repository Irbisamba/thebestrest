package ru.yastrebova.thebestrest;

import lombok.Data;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yastrebova.thebestrest.model.response.RestaurantMeal;
import ru.yastrebova.thebestrest.service.UserService;

import java.util.List;

@Controller
@Data
public class UserController {

    private final UserService userService;



    @GetMapping("/get-restaurants")
    public ResponseEntity<List<RestaurantMeal>> getAllRestaurants() {
        return new ResponseEntity<>(userService.getRestaurantMealList(), HttpStatus.OK);
    }

    @GetMapping("/{id}/vote")
    public ResponseEntity<String> vote(@PathVariable("id") Integer userId, @RequestParam Integer restaurantId) {
        userService.vote(userId, restaurantId);
        return new ResponseEntity<>("Your vote registered", HttpStatus.OK);
    }
}
