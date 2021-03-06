package ru.yastrebova.thebestrest.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "meals_history")
@Data
public class Meal {
    public static final int START_SEQ = 1;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "integer default nextval('global_seq')")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

    @Column(name = "meal_title", nullable = false)
    @NotBlank
    @Size(max = 255)
    String mealTitle;

    @Column(name = "price", nullable = false)
    @NotNull
    int price;

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    @Column(name = "restaurant_id")
    @NotNull
    private Integer restaurantId;

    public Meal() {
    }

    public Meal(@NotBlank String mealTitle, @NotNull int price, @NotNull int restaurantId) {
        this.mealTitle = mealTitle;
        this.price = price;
        this.date = LocalDate.now();
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", mealTitle='" + mealTitle + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
