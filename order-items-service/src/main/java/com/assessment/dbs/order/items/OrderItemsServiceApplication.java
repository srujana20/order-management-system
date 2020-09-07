package com.assessment.dbs.order.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.assessment"})
@EnableJpaRepositories(basePackages = {"com.assessment.dbs.order.items.repository"})
@EntityScan(basePackages = {"com.assessment.db.domain"})
@EnableEurekaClient
public class OrderItemsServiceApplication 
{
    public static void main( String[] args )
    {
       SpringApplication.run(OrderItemsServiceApplication.class, args);
    }
}
