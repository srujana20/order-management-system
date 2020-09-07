package com.assessment.dbs.order.service.api;

import java.util.List;

import com.assessment.db.domain.Order;
import com.assessment.db.domain.OrderItem;



public interface OrderService {

	public List<Order> getAllOrders();
	public Order addOrder(Order order);
	public Order getOrderById(Long id);
	public List<OrderItem> fetchItems(Long orderId);
	
}
