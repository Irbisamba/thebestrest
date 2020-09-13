package ru.yastrebova.thebestrest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yastrebova.thebestrest.model.response.RestaurantMeal;
import ru.yastrebova.thebestrest.service.RestaurantService;
import ru.yastrebova.thebestrest.service.UserService;

import java.util.List;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/save")
    public ResponseEntity<String> save(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        userService.save(name, email, password);
        return new ResponseEntity<>("Успешно", HttpStatus.OK);
    }

    @GetMapping("/get-restaurants")
    public ResponseEntity<List<RestaurantMeal>> getAllRestaurants() {
        log.error("AZAZA");
        return new ResponseEntity<>(userService.getRestaurantMealList(), HttpStatus.OK);
    }

    @GetMapping("/{id}/vote")
    public ResponseEntity<String> vote(@PathVariable("id") Integer userId, @RequestParam Integer restaurantId) {
        userService.vote(userId, restaurantId);
        return new ResponseEntity<>("Your vote registered", HttpStatus.OK);
    }
}
