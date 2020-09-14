package ru.yastrebova.thebestrest.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;


@Data
@Accessors(chain = true)
public class RestaurantMeal {
    Integer restaurantId;
    String restaurantName;
    Integer rating;
    Map<String, Integer> meals;
}
