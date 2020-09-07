package com.assessment.dbs.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.assessment.db.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
