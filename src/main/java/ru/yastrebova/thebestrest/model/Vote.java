package ru.yastrebova.thebestrest.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "voting_history")
@Data
public class Vote {
    public static final int START_SEQ = 1;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "integer default nextval('global_seq')")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "user_id", nullable = false)
    @NotNull
    private Integer userId;

    @Column(name = "restaurant_id", nullable = false)
    @NotNull
    private Integer restaurantId;

    @Column(name = "date_of_voting", nullable = false)
    @NotNull
    private LocalDate dateOfVoting;

    public Vote() {
    }

    public Vote(Integer userId, Integer restaurantId) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.dateOfVoting = LocalDate.now();
    }
}
