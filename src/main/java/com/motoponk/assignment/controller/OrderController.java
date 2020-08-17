package com.motoponk.assignment.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.motoponk.assignment.model.dto.OrderDTO;
import com.motoponk.assignment.model.dto.OrderRequestDTO;
import com.motoponk.assignment.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/orders")
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public OrderDTO saveOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        OrderDTO orderDTO = orderService.saveOrder(orderRequestDTO);
        log.info("Order has been saved successfully for Order DTO: {}", orderDTO);
        return orderDTO;
    }
    
    @GetMapping
    public List<OrderDTO> retreiveOrders(
            @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate, 
            @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME)LocalDateTime endDate) {
        
        // TODO Apply better date conversion.. Start and date can have default value(s)..
        
        // TODO Check startDate <= endDate --> throw new Exception..
        // TODO Check startDate - endDate diff... Do not allow more then 6 months??
        
        return orderService.retreiveOrders(startDate, endDate);
    }
    
}
