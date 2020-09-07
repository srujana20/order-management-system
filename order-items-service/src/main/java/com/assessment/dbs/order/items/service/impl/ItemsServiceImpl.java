package com.assessment.dbs.order.items.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.assessment.db.domain.OrderItem;
import com.assessment.dbs.order.items.exception.DbException;
import com.assessment.dbs.order.items.exception.UniqueConstraintViolationException;
import com.assessment.dbs.order.items.repository.OrderItemsRepositoy;
import com.assessment.dbs.order.items.service.api.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	OrderItemsRepositoy repo;

	public List<OrderItem> getAllItemsOfOrder(long id) {
		List<OrderItem> items = null;
		try {
			items = repo.findAllByOrderId(id);
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		return items;

	}

	public void addItems(List<OrderItem> items) {
		try {
		repo.saveAll(items);
		}catch (DataIntegrityViolationException e) {
			throw new UniqueConstraintViolationException(
					"Integrity constraint exception, conflicting values provided in request");
		}catch(Exception e) {
			throw new DbException(e.getMessage());
		}
	}

}
