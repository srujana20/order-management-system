package com.assessment.dbs.order.items;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.assessment.dbs.order.items.repository.OrderItemsRepositoy;
import com.assessment.dbs.order.items.service.api.ItemsService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderItemsControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ItemsService itemsService;


	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	OrderItemsRepositoy repo;

	

	@BeforeEach
	public void Setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@Test
	public void testGetItems() throws Exception {
		mockMvc.perform(get("/api/order/items/{id}",1)).andExpect(status().isOk());
	}

}
