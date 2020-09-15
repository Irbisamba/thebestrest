package ru.yastrebova.thebestrest;

import org.junit.Test;
import org.mockito.Mock;
import ru.yastrebova.thebestrest.exception.AlreadyVotedException;
import ru.yastrebova.thebestrest.model.Restaurant;
import ru.yastrebova.thebestrest.model.Vote;
import ru.yastrebova.thebestrest.repository.RestaurantRepository;
import ru.yastrebova.thebestrest.service.UserService;
import ru.yastrebova.thebestrest.util.TimeUtil;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private static final LocalTime TIME = LocalTime.of(11, 0, 0);

    @Mock
    RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);

    @Mock
    TimeUtil timeUtil = mock(TimeUtil.class);

    UserService userService = new UserService(restaurantRepository, timeUtil);


    @Test
    public void vote_voteIsNull() {
        when(restaurantRepository.getVote(1)).thenReturn(null);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(11);
        restaurant.setName("TwinPigs");
        restaurant.setAddress("Moscow");
        when(restaurantRepository.findRestaurant(11)).thenReturn(restaurant);

        userService.vote(1, 11);
        assertEquals(1, restaurant.getRating());
    }

    @Test
    public void vote_voteIsOld() {
        Vote vote = new Vote();
        vote.setDateOfVoting(LocalDate.now().minusDays(2));
        when(restaurantRepository.getVote(1)).thenReturn(vote);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(11);
        restaurant.setName("TwinPigs");
        restaurant.setAddress("Moscow");
        restaurant.setRating(1);
        when(restaurantRepository.findRestaurant(11)).thenReturn(restaurant);

        userService.vote(1, 11);
        assertEquals(2, restaurant.getRating());
    }

    @Test
    public void vote_voteMadeTodayBefore11() {
        Vote vote = new Vote();
        vote.setDateOfVoting(LocalDate.now());
        vote.setUserId(1);
        vote.setRestaurantId(11);
        when(restaurantRepository.getVote(1)).thenReturn(vote);
        Restaurant oldRestaurant = new Restaurant();
        oldRestaurant.setId(11);
        oldRestaurant.setName("TwinPigs");
        oldRestaurant.setAddress("Moscow");
        oldRestaurant.setRating(2);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(13);
        restaurant.setName("Fresh");
        restaurant.setAddress("Moscow");
        restaurant.setRating(2);
        when(restaurantRepository.findRestaurant(11)).thenReturn(oldRestaurant);
        when(restaurantRepository.findRestaurant(13)).thenReturn(restaurant);
        when(timeUtil.now()).thenReturn(TIME.minusHours(1));

        userService.vote(1, 13);
        assertEquals(1, oldRestaurant.getRating());
        assertEquals(3, restaurant.getRating());
    }

    @Test
    public void vote_voteMadeTodayAfter11() {
        Vote vote = new Vote();
        vote.setDateOfVoting(LocalDate.now());
        vote.setUserId(1);
        vote.setRestaurantId(11);
        when(restaurantRepository.getVote(1)).thenReturn(vote);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(13);
        restaurant.setName("Fresh");
        restaurant.setAddress("Moscow");
        restaurant.setRating(2);
        when(restaurantRepository.findRestaurant(13)).thenReturn(restaurant);
        when(timeUtil.now()).thenReturn(TIME.plusHours(2));

        assertThrows(AlreadyVotedException.class, () -> userService.vote(1, 13));
    }
}
