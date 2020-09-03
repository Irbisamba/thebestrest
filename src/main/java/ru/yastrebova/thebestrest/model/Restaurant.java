package ru.yastrebova.thebestrest.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "restaurants")
@Data
public class Restaurant {
    public static final int START_SEQ = 1;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "integer default nextval('global_seq')")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String name;

    @Column(name = "address")
    @NotBlank
    @Size(max = 255)
    private String address;

    @Column(name = "date_of_last_updating")
    @NotNull
    private LocalDate dateOfLastUpdating;

    @Column(name = "rating")
    @NotNull
    private int rating;

    @Column(name = "admin_id")
    @NotNull
    private int admin_id;
}
