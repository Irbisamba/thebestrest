package ru.yastrebova.thebestrest.model.response;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class RestaurantMeal {
    String restaurantName;
    String mealTitle;
    Integer price;

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }
}
