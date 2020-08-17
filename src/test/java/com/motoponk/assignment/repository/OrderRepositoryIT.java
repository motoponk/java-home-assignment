package com.motoponk.assignment.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import com.motoponk.assignment.model.entity.Order;
import com.motoponk.assignment.util.ProductTestUtil;

@DataJdbcTest
public class OrderRepositoryIT {

    @Autowired
    private OrderRepository orderRepository;
    
    @Test
    public void shouldRetreiveOrdersAfterAdditionProperly() {
        assertTrue(orderRepository.findAll().isEmpty());
        
        Order order = ProductTestUtil.createSampleOrder();
        orderRepository.save(order);
        
        assertFalse(orderRepository.findAll().isEmpty());
        assertTrue(orderRepository.findAll().size() == 1);
    }
    
}
