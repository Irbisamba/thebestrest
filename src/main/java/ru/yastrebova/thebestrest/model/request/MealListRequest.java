package ru.yastrebova.thebestrest.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MealListRequest {
    @NotNull
    private Integer restaurantId;

    @NotNull
    private List<Meal> meals;

    @Data
    public static  class Meal {
        @NotBlank
        private String mealTitle;
        @NotNull
        private Integer price;
    }
}
