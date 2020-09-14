package ru.yastrebova.thebestrest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yastrebova.thebestrest.model.Meal;
import ru.yastrebova.thebestrest.model.Restaurant;
import ru.yastrebova.thebestrest.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RestaurantRepository {

    CrudRestaurantRepository crudRestaurantRepository;

    CrudMealRepository crudMealRepository;

    CrudVoteRepository crudVoteRepository;

    @Autowired
    public RestaurantRepository(CrudRestaurantRepository crudRestaurantRepository, CrudMealRepository crudMealRepository, CrudVoteRepository crudVoteRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudMealRepository = crudMealRepository;
        this.crudVoteRepository = crudVoteRepository;
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        restaurant.setDateOfLastUpdating(LocalDate.now());
        return crudRestaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return crudRestaurantRepository.findAll();
    }

    public List<Restaurant> getAllRestaurantsForAdmin(Integer adminId) {
        return crudRestaurantRepository.getRestaurantsForAdmin(adminId);
    }

    public Restaurant findRestaurant(Integer restaurantId) {
        return crudRestaurantRepository.findById(restaurantId).orElse(null);
    }

    public Meal addMeal(Meal meal) {
        return crudMealRepository.save(meal);
    }

    public Vote getVote(Integer userId) {
        return crudVoteRepository.getForUser(userId).orElse(null);
    }

    public void saveVote(Vote vote) {
        crudVoteRepository.save(vote);
    }


}
