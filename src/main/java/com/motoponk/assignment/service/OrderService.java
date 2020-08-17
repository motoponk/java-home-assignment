package com.motoponk.assignment.service;

import java.time.LocalDateTime;
import java.util.List;

import com.motoponk.assignment.model.dto.OrderDTO;
import com.motoponk.assignment.model.dto.OrderRequestDTO;

public interface OrderService {

    OrderDTO saveOrder(OrderRequestDTO orderDTO);

    List<OrderDTO> retreiveOrders(LocalDateTime startDate, LocalDateTime endDate);

}
