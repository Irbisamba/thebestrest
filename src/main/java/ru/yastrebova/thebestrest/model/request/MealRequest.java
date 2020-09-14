package ru.yastrebova.thebestrest.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class MealRequest {

    @NotNull
    private Integer restaurantId;
    @NotBlank
    private String mealTitle;
    @NotNull
    private Integer price;
}
