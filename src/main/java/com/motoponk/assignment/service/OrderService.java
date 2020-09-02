package com.motoponk.assignment.service;

import java.time.LocalDateTime;
import java.util.List;

import com.motoponk.assignment.model.dto.OrderDTO;
import com.motoponk.assignment.model.dto.OrderRequestDTO;

public interface OrderService {

    OrderDTO saveOrder(String email, OrderRequestDTO orderDTO);

    List<OrderDTO> retreiveOrders(String email, LocalDateTime startDate, LocalDateTime endDate);

}
