package ru.yastrebova.thebestrest.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Data
public class User {
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

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 255)
    private String password;

    public User(@NotBlank @Size(max = 255) String name, @Email @NotBlank @Size(max = 255) String email, @NotBlank @Size(min = 5, max = 255) String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
