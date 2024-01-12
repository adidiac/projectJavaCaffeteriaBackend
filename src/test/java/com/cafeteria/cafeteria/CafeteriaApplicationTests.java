package com.cafeteria.cafeteria;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cafeteria.cafeteria.controllers.AnalyticsController;
import com.cafeteria.cafeteria.controllers.FeedbackController;
import com.cafeteria.cafeteria.controllers.InventoryController;
import com.cafeteria.cafeteria.controllers.MenuItemController;
import com.cafeteria.cafeteria.controllers.OrderController;
import com.cafeteria.cafeteria.controllers.PromotionController;
import com.cafeteria.cafeteria.controllers.UserController;

@SpringBootTest
class CafeteriaApplicationTests {

	@Autowired
	private AnalyticsController analyticsController;
	@Autowired
	private MenuItemController menuItemController;
	@Autowired
	private OrderController orderController;
	@Autowired
	private PromotionController promotionController;
	@Autowired
	private UserController userController;
	@Autowired
	private InventoryController inventoryController;
	@Autowired
	private FeedbackController feedbackController;

	private MockMvc mockMvcAnalytics;
	private MockMvc mockMvcMenuItem;
	private MockMvc mockMvcOrder;
	private MockMvc mockMvcPromotion;
	private MockMvc mockMvcUser;
	private MockMvc mockMvcFeeedback;
	private MockMvc mockMvcInventory;


	@BeforeEach
    public void setup() {
		mockMvcAnalytics = MockMvcBuilders.standaloneSetup(analyticsController).build();
		mockMvcMenuItem = MockMvcBuilders.standaloneSetup(menuItemController).build();
		mockMvcOrder = MockMvcBuilders.standaloneSetup(orderController).build();
		mockMvcPromotion = MockMvcBuilders.standaloneSetup(promotionController).build();
		mockMvcUser = MockMvcBuilders.standaloneSetup(userController).build();
		mockMvcInventory = MockMvcBuilders.standaloneSetup(inventoryController).build();
		mockMvcFeeedback = MockMvcBuilders.standaloneSetup(feedbackController).build();
	}

	@Test
	void testGetMenuItems() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/menu-items").accept(MediaType.APPLICATION_JSON);
		mockMvcMenuItem.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testCreateMenuItem() throws Exception {
		var JwtToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJhZG1pbiIsImlhdCI6MTcwNDU4MDEyOSwiZXhwIjoxNzA0NjY2NTI5fQ.K-kStlutbOV7vtJYf3yf0pgiBpVzTBase4i0LbjBZgA";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/menu-items").header("Authorization", JwtToken)
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"test\",\"description\":\"test\",\"price\":\"1\",\"category\":\"test\"}")
				.contentType(MediaType.APPLICATION_JSON);
		
		mockMvcMenuItem.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	// write tests for all the other controllers
	@Test
	void testDeleteMenuItem() throws Exception {
		var JwtToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJhZG1pbiIsImlhdCI6MTcwNDU4MDEyOSwiZXhwIjoxNzA0NjY2NTI5fQ.K-kStlutbOV7vtJYf3yf0pgiBpVzTBase4i0LbjBZgA";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/menu-items/1").header("Authorization", JwtToken)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvcMenuItem.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testUpdateMenuItem() throws Exception {
		var JwtToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJhZG1pbiIsImlhdCI6MTcwNDU4MDEyOSwiZXhwIjoxNzA0NjY2NTI5fQ.K-kStlutbOV7vtJYf3yf0pgiBpVzTBase4i0LbjBZgA";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/menu-items/2").header("Authorization", JwtToken)
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"test\",\"description\":\"test\",\"price\":\"1\",\"category\":\"test\"}")
				.contentType(MediaType.APPLICATION_JSON);
		
		mockMvcMenuItem.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetOrders() throws Exception {
		var JwtToken =  "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJhZG1pbiIsImlhdCI6MTcwNDU4MDEyOSwiZXhwIjoxNzA0NjY2NTI5fQ.K-kStlutbOV7vtJYf3yf0pgiBpVzTBase4i0LbjBZgA";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/orders").header("Authorization", JwtToken)
				.accept(MediaType.APPLICATION_JSON);
		mockMvcOrder.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testLogin() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/login")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"admin\",\"password\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON);
		
		mockMvcUser.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetPromotions() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/promotions")
				.accept(MediaType.APPLICATION_JSON);
		mockMvcPromotion.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testRegister () throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/register")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"test\",\"password\":\"test\",\"role\":\"test\"}")
				.contentType(MediaType.APPLICATION_JSON);
		
		mockMvcUser.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}
}