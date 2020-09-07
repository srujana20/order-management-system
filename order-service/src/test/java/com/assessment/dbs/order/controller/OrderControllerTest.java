package com.assessment.dbs.order.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.assessment.db.domain.Order;
import com.assessment.db.domain.OrderItem;
import com.assessment.dbs.order.remote.ItemsClient;
import com.assessment.dbs.order.repository.OrderRepository;
import com.assessment.dbs.order.service.api.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	OrderService orderService;

	@MockBean
	ItemsClient client;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	OrderRepository orderRepo;

	ObjectMapper mapper = new ObjectMapper();
	Order order = new Order();
	@BeforeEach
	public void setUp() {
		
		OrderItem item = new OrderItem();
		item.setPrice(1);
		item.setProductCode(101);
		item.setProductName("a");
		item.setQuantity(1);
		List<OrderItem> ls = new ArrayList<OrderItem>();
		ls.add(item);
		order.setCost(1);
		order.setCustomerName("abc");
		order.setItems(ls);
		order.setShippingAddress("abc");
	}

	@BeforeEach
	public void Setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@Test
	public void testGetOrders() throws Exception {
		mockMvc.perform(get("/api/orders")).andExpect(status().isOk());
		verify(orderService, times(1)).getAllOrders();
	}
	
	@Test
	public void testAddOrders() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String orderJson = mapper.writeValueAsString(order);
		mockMvc.perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON).content(orderJson))
		.andExpect(status().isCreated()).andReturn();
	}
	
	@Test
	public void testAddOrdersWithInvalidPayload() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		order.setCustomerName(null);
		String orderJson = mapper.writeValueAsString(order);
		mockMvc.perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON).content(orderJson))
		.andExpect(status().isBadRequest()).andReturn();
	}

	
	@Test
	public void testAddOrdersWithNullPayload() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String orderJson = mapper.writeValueAsString(null);
		mockMvc.perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON).content(orderJson))
		.andExpect(status().isBadRequest()).andReturn();
	}
	
	@Test
	public void testGetOrderById() throws Exception {
		mockMvc.perform(get("/api/orders/{id}",2))
		.andExpect(status().isOk()).andReturn();
	}


}
