package com.assessment.dbs.order.remote;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.assessment.db.domain.OrderItem;


@FeignClient(value="items-service",url = "http://localhost:8082/api")
public interface ItemsClient {
    @RequestMapping(method = RequestMethod.GET, value = "/order/items/{orderId}")
    List<OrderItem> getItemsOfOrder(@PathVariable(name = "orderId") Long orderId);
    
}