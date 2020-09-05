package ru.yastrebova.thebestrest.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MealRequest {
    @NotNull
    public Integer restaurantId;
    @NotBlank
    public String mealTitle;
    @NotNull
    public Integer price;
}
