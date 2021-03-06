package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;


    @GetMapping("/restaurants")
    public List<Restaurant> list(){
//        List<Restaurant> restaurants = new ArrayList<>();
//        Restaurant restaurant = new Restaurant(1004L,"Bob zip","Seoul");
//        restaurants.add(restaurant);

        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
        Restaurant restaurant = restaurantService.getRestaurant(id);

        //Restaurant restaurant = restaurantRepository.findById(id);


//        Restaurant restaurant = restaurants.stream()
//                .filter(r -> r.getId().equals(id))
//                .findFirst()
//                .orElse(null);

        return restaurant;
    }
}
