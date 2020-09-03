package ru.yastrebova.thebestrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yastrebova.thebestrest.model.Restaurant;
import ru.yastrebova.thebestrest.service.RestaurantService;
import ru.yastrebova.thebestrest.service.UserService;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/save")
    public ResponseEntity save(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        userService.save(name, email, password);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/get-restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }
}
