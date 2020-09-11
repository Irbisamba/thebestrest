package ru.yastrebova.thebestrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yastrebova.thebestrest.exception.AlreadyVotedException;
import ru.yastrebova.thebestrest.model.Restaurant;
import ru.yastrebova.thebestrest.model.User;
import ru.yastrebova.thebestrest.model.Vote;
import ru.yastrebova.thebestrest.model.response.RestaurantMeal;
import ru.yastrebova.thebestrest.repository.RestaurantRepository;
import ru.yastrebova.thebestrest.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final LocalTime FINISH_VOTING = LocalTime.of(11, 0, 0);

    @Autowired
    private UserRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public User save(String name, String email, String password) {
        User user = new User(name, email, password);
        user = repository.save(user);
        System.out.println(user);
        return user;
    }

    public void vote(Integer userId, Integer restaurantId) {
        Vote vote = restaurantRepository.getVote(userId);
        if (vote == null || !vote.getDateOfVoting().equals(LocalDate.now())) {
            Restaurant restaurant = restaurantRepository.findRestaurant(restaurantId);
            restaurant.setRating(restaurant.getRating() + 1);
            restaurantRepository.save(restaurant);
            Vote newVote = new Vote(userId, restaurantId);
            restaurantRepository.saveVote(newVote);
        } else if(vote.getDateOfVoting().equals(LocalDate.now())) {
                    if(LocalTime.now().isBefore(FINISH_VOTING)) {
                        Restaurant restaurant = restaurantRepository.findRestaurant(restaurantId);
                        restaurant.setRating(restaurant.getRating() + 1);
                        restaurantRepository.save(restaurant);
                        Vote newVote = new Vote(userId, restaurantId);
                        restaurantRepository.saveVote(newVote);
                    } else {
                        throw new AlreadyVotedException("You have already voted today");
                    }
        }
    }

    public List<RestaurantMeal> getRestaurantMealList() {
        return restaurantRepository.getAllRestaurants().stream()
                .filter(m -> m.getMealTitle() != null && m.getMealPrice() != null && m.getDateOfLastUpdating().isEqual(LocalDate.now()))
                .map(m ->
                        new RestaurantMeal()
                                .setRestaurantId(m.getId())
                                .setRestaurantName(m.getName())
                                .setMealTitle(m.getMealTitle())
                                .setPrice(m.getMealPrice()))
                .collect(Collectors.toList());
    }
}
