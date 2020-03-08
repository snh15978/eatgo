package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));

        given(restaurantService.getRestaurants()).willReturn(restaurants);
        mockMvc.perform(get("/restaurants"))
                 .andExpect(status().isOk())
                 .andExpect(content().string(
                      containsString("\"id\":1004")))
                .andExpect(content().string(
                     containsString("\"name\":\"Bob zip\"")));
    }

    @Test
    public void detail() throws Exception {

        Restaurant restaurant = new Restaurant(1002L, "Cyber Food", "Seoul");
        restaurant.addMenuItem(new MenuItem("kimchi"));

        given(restaurantService.getRestaurant(1002L)).willReturn(restaurant);

        mockMvc.perform(get("/restaurants/1002"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1002")))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber Food\"")))
                .andExpect(content().string(
                        containsString("kimchi")
                ));
    }
}