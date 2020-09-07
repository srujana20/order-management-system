package com.assessment.dbs.order.items.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.db.domain.OrderItem;
import com.assessment.dbs.order.items.service.api.ItemsService;


@RestController
@RequestMapping(ItemsController.ENDPOINT)
@Api(value="Items Management", description="Operations pertaining to items in an order management system")
public class ItemsController {

	

	public static final String ENDPOINT = "/api/order/items";


	@Autowired
	private ItemsService orderService;
	

	@ApiOperation("Fetch all items of an order")
	@GetMapping("/{orderId}")
	public List<OrderItem> getAllItemsOfOrder(@PathVariable Long orderId) {
		return orderService.getAllItemsOfOrder(orderId);
	}
	
	
}


