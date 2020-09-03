package ru.yastrebova.thebestrest.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RestaurantRequest {
    @NotBlank
    public String name;
    public String address;
    @NotNull
    public int adminId;
}
