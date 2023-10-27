package com.orders.config;

import com.orders.entity.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public Order order() {
        return new Order();
    }
}
