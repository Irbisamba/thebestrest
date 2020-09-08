package ru.yastrebova.thebestrest.model.response;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class RestaurantMeal {
    Integer restaurantId;
    String restaurantName;
    String mealTitle;
    Integer price;
}