package com.motoponk.assignment.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.motoponk.assignment.model.dto.OrderDTO;
import com.motoponk.assignment.model.entity.Order;
import com.motoponk.assignment.model.entity.Product;
import com.motoponk.assignment.repository.OrderRepository;
import com.motoponk.assignment.repository.ProductRepository;
import com.motoponk.assignment.util.ProductTestUtil;

public class OrderServiceImplTest {

    private OrderRepository orderRepository = mock(OrderRepository.class);
    private ProductRepository productRepository = mock(ProductRepository.class);
    
    private OrderServiceImpl orderService = 
            new OrderServiceImpl(orderRepository, productRepository);
    
    @Test
    public void shouldSaveOrderProperly() {
        OrderDTO orderDTO = ProductTestUtil.createSampleOrderDTO();
        Order order = ProductTestUtil.createSampleOrder();
        Product product = ProductTestUtil.createSampleProduct();
        
        given(productRepository.findBySku(ProductTestUtil.SAMPLE_PRODUCT_1_SKU))
            .willReturn(Optional.of(product));
        
        when(orderRepository.save(any())).thenReturn(order);
        
        orderService.saveOrder(orderDTO);
    }
    
    @Test
    public void shouldRetreiveOrdersProperly() {

        Order order = ProductTestUtil.createSampleOrder();
        
        LocalDateTime start = LocalDateTime.of(2020, 7, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.now();
        
        given(orderRepository.findAllByCreatedTimeGreaterThanEqualAndCreatedTimeLessThanEqual(start, end))
                             .willReturn(Arrays.asList(order));
        
        List<OrderDTO> orders = orderService.retreiveOrders(start, end);
        
        assertTrue(orders.size() == 1);
    }
    
}
