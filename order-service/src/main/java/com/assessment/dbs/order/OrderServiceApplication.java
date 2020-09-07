package com.assessment.dbs.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.assessment"})
@EnableJpaRepositories(basePackages = {"com.assessment.dbs.order.repository"})
@EntityScan(basePackages = {"com.assessment.db.domain"})
@EnableEurekaClient
@EnableHystrix
public class OrderServiceApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(OrderServiceApplication.class, args);
    }
}
