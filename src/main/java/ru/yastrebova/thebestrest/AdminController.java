package ru.yastrebova.thebestrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.yastrebova.thebestrest.model.Restaurant;
import ru.yastrebova.thebestrest.model.request.RestaurantRequest;
import ru.yastrebova.thebestrest.service.RestaurantService;

@Controller
@RequestMapping("/admin")
@ResponseBody
public class AdminController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/restaurant/create")
    @ResponseBody
    public ResponseEntity createRestaurant(@RequestParam String name,@RequestParam(required = false)  String address,@RequestParam int adminId) {
        restaurantService.create(name, address, adminId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
