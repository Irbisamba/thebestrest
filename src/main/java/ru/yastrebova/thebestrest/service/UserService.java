package ru.yastrebova.thebestrest.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yastrebova.thebestrest.exception.AlreadyVotedException;
import ru.yastrebova.thebestrest.exception.RestaurantNotFoundException;
import ru.yastrebova.thebestrest.model.Restaurant;
import ru.yastrebova.thebestrest.model.Vote;
import ru.yastrebova.thebestrest.model.response.RestaurantMeal;
import ru.yastrebova.thebestrest.repository.RestaurantRepository;
import ru.yastrebova.thebestrest.util.TimeUtil;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Data
public class UserService {
    private static final LocalTime FINISH_VOTING = LocalTime.of(11, 0, 0);

    private final RestaurantRepository restaurantRepository;

    private final TimeUtil timeUtil;

    public synchronized void vote(Integer userId, Integer restaurantId) {
        Vote vote = restaurantRepository.getVote(userId);
        Restaurant restaurant = restaurantRepository.findRestaurant(restaurantId);
        if(restaurant==null) {
            throw new RestaurantNotFoundException(String.format("Restaurant with id - %d not found in DataBase", restaurantId));
        }
        if (vote == null || !vote.getDateOfVoting().equals(LocalDate.now())) {
            restaurant.setRating(restaurant.getRating() + 1);
            restaurantRepository.save(restaurant);
            Vote newVote = new Vote(userId, restaurantId);
            restaurantRepository.saveVote(newVote);
            log.debug("Rating for restaurant " + restaurant.getName() + " increased : " + restaurant.getRating());

        } else if(vote.getDateOfVoting().equals(LocalDate.now())) {
                    if(timeUtil.now().isBefore(FINISH_VOTING)) {
                        Restaurant oldRestaurant = restaurantRepository.findRestaurant(vote.getRestaurantId());
                        if (oldRestaurant==null) {
                            log.error(String.format("Restaurant with id - %d not found in DataBase", vote.getRestaurantId()));
                        } else {
                            oldRestaurant.setRating(oldRestaurant.getRating() - 1);
                            restaurantRepository.save(oldRestaurant);
                            log.debug("Rating for restaurant " + oldRestaurant.getName() + " decreased : " + oldRestaurant.getRating());
                        }
                        restaurant.setRating(restaurant.getRating() + 1);
                        restaurantRepository.save(restaurant);
                        Vote newVote = new Vote(userId, restaurantId);
                        restaurantRepository.saveVote(newVote);
                        log.debug("Rating for restaurant " + restaurant.getName() + " increased : " + restaurant.getRating());
                    } else {
                        throw new AlreadyVotedException("You have already voted today");
                    }
        }
    }

    public List<RestaurantMeal> getRestaurantMealList() {
        return restaurantRepository.getAllRestaurants().stream()
                .filter(m -> m.getMeals()!=null && !m.getMeals().isEmpty() && m.getDateOfLastUpdating().isEqual(LocalDate.now()))
                .map(m ->
                        new RestaurantMeal()
                                .setRestaurantId(m.getId())
                                .setRestaurantName(m.getName())
                                .setRating(m.getRating())
                                .setMeals(m.getMeals()))
                .collect(Collectors.toList());
    }
}
