package com.motoponk.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.motoponk.assignment.model.dto.OrderDTO;
import com.motoponk.assignment.service.OrderService;
import com.motoponk.assignment.util.ProductTestUtil;

public class OrderControllerTest {

    private OrderService orderService = mock(OrderService.class);
    
    private OrderController orderController = new OrderController(orderService);
    
    @Test
    public void shouldSaveOrderProperly() {
        OrderDTO orderDTO = ProductTestUtil.createSampleOrderDTO();
        orderController.saveOrder(orderDTO);
    }
    
    @Test
    public void shouldRetreiveOrdersProperly() {
        OrderDTO orderDTO = ProductTestUtil.createSampleOrderDTO();
        
        LocalDateTime start = LocalDateTime.of(2020, 7, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.now();
        given(orderService.retreiveOrders(start, end)).willReturn(Arrays.asList(orderDTO));
        
        List<OrderDTO> orders = orderController.retreiveOrders(start, end);
        
        assertTrue(orders.size() == 1);
    }
    
}
