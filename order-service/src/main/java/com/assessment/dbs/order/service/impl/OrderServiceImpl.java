package com.assessment.dbs.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.assessment.db.domain.Order;
import com.assessment.db.domain.OrderItem;
import com.assessment.dbs.order.exception.DbException;
import com.assessment.dbs.order.exception.FeignClientException;
import com.assessment.dbs.order.exception.NotFoundException;
import com.assessment.dbs.order.exception.UniqueConstraintViolationException;
import com.assessment.dbs.order.remote.ItemsClient;
import com.assessment.dbs.order.repository.OrderRepository;
import com.assessment.dbs.order.service.api.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository repo;

	@Autowired
	ItemsClient client;

	public List<Order> getAllOrders() {
		return repo.findAll();
	}

	public Order addOrder(Order order) {
		Order orderRes = null;
		try {
			orderRes = repo.save(order);
		} catch (DataIntegrityViolationException e) {
			throw new UniqueConstraintViolationException(
					"Integrity constraint exception, conflicting values provided in request");
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		return orderRes;

	}

	public Order getOrderById(Long id) {
		return repo.findById(id).orElseThrow(() -> new NotFoundException("Instance not found with id" + " " + id));
	}

	public List<OrderItem> fetchItems(Long orderId) {
		List<OrderItem> items = null;
		try {
			items = client.getItemsOfOrder(orderId);
		} catch (Exception e) {
			throw new FeignClientException(e.getMessage());
		}
		return items;
	}
}
