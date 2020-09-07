package com.assessment.dbs.order.items.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.assessment.db.domain.OrderItem;


public  interface OrderItemsRepositoy extends JpaRepository<OrderItem, Long>{
	
	@Query(value="SELECT * FROM item_table u WHERE u.order_id = :orderId",nativeQuery = true)
	public List<OrderItem> findAllByOrderId(@Param("orderId")Long orderId);
	

}
