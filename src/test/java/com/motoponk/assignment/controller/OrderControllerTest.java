package com.motoponk.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import com.motoponk.assignment.model.dto.OrderDTO;
import com.motoponk.assignment.service.OrderService;
import com.motoponk.assignment.util.ProductTestUtil;

public class OrderControllerTest {
    
    private Authentication authentication = mock(Authentication.class);
    
    private OrderService orderService = mock(OrderService.class);
    
    private OrderController orderController = new OrderController(orderService);
    
    
    @Test
    public void shouldSaveOrderProperly() {
        OrderDTO orderDTO = ProductTestUtil.createSampleOrderDTO();
        orderController.saveOrder(orderDTO, authentication);
    }
    
    @Test
    public void shouldRetreiveOrdersProperly() {
        OrderDTO orderDTO = ProductTestUtil.createSampleOrderDTO();
        
        LocalDateTime start = LocalDateTime.of(2020, 7, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.now();
        
        given(authentication.getPrincipal()).willReturn(ProductTestUtil.createSampleAuthenticationUser());
        given(orderService.retreiveOrders(ProductTestUtil.SAMPLE_EMAIL, start, end))
                .willReturn(Arrays.asList(orderDTO));
        
        List<OrderDTO> orders = orderController.retreiveOrders(start, end, authentication);
        
        assertTrue(orders.size() == 1);
    }
    
    @Test
    public void shouldReturnEmptyResultForEmptyAuthentication() {
        OrderDTO orderDTO = ProductTestUtil.createSampleOrderDTO();
        LocalDateTime start = LocalDateTime.of(2020, 7, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.now();
        
        given(orderService.retreiveOrders(ProductTestUtil.SAMPLE_EMAIL, start, end))
                .willReturn(Arrays.asList(orderDTO));
        
        List<OrderDTO> orders = orderController.retreiveOrders(start, end, authentication);
        assertTrue(orders.isEmpty());
    }
    
    
    @Test
    public void shouldReturnEmptyResultForUnknownPrincipalType() {
        OrderDTO orderDTO = ProductTestUtil.createSampleOrderDTO();
        LocalDateTime start = LocalDateTime.of(2020, 7, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.now();
        
        given(authentication.getPrincipal()).willReturn(ProductTestUtil.SAMPLE_EMAIL);
        given(orderService.retreiveOrders(ProductTestUtil.SAMPLE_EMAIL, start, end))
                .willReturn(Arrays.asList(orderDTO));
        
        List<OrderDTO> orders = orderController.retreiveOrders(start, end, authentication);
        assertTrue(orders.isEmpty());
    }
}
