package com.assessment.dbs.order.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import javax.validation.Valid;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.assessment.db.domain.Order;
import com.assessment.db.domain.OrderItem;
import com.assessment.dbs.order.remote.ItemsClient;
import com.assessment.dbs.order.service.api.OrderService;

@RestController
@RequestMapping(OrderController.ENDPOINT)
@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = "Orders")
@EnableHystrix
public class OrderController {

	public static final String ENDPOINT = "/api/orders";

	@Autowired
	private OrderService orderService;

	@Autowired
	ItemsClient client;

	
	@ApiOperation("Add an order")
	@PostMapping
	public ResponseEntity<Order> addOrder(@Valid @RequestBody Order project) {
		return new ResponseEntity<Order>(orderService.addOrder(project), HttpStatus.CREATED);
	}

	@ApiOperation("Fetch items of an order")
	@GetMapping("/items/{id}")
	@HystrixCommand(fallbackMethod = "fallback_get", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
			})
	public ResponseEntity<List<OrderItem>> fetchItems(@PathVariable Long id) {
		return new ResponseEntity<List<OrderItem>>(client.getItemsOfOrder(id), HttpStatus.OK);
	}

	@ApiOperation("Fetch an order")
	@GetMapping("/{id}")
	public ResponseEntity<Order> fetchOrder(@PathVariable Long id) {
		return new ResponseEntity<Order>(orderService.getOrderById(id), HttpStatus.OK);
	}
	
	private ResponseEntity<List<OrderItem>> fallback_get(Long id) {
		   return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}

}
