package ru.yastrebova.thebestrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.yastrebova.thebestrest.model.Restaurant;

import java.util.List;



@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT r FROM Restaurant r WHERE r.adminId=:adminId")
    List<Restaurant> getRestaurantsForAdmin(@Param("adminId") Integer adminId);
}
