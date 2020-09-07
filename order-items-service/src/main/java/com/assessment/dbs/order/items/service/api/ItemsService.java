package com.assessment.dbs.order.items.service.api;

import java.util.List;

import com.assessment.db.domain.OrderItem;


public interface ItemsService {
	
	public List<OrderItem> getAllItemsOfOrder(long id);
	public void addItems(List<OrderItem> items);
	

}
