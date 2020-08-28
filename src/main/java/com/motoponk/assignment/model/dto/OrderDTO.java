package com.motoponk.assignment.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import com.motoponk.assignment.model.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class OrderDTO extends OrderRequestDTO {

    private LocalDateTime orderTime;
    private BigDecimal totalPrice;

    public OrderDTO(Order order) {
        setEmail(order.getEmail());
        setProducts(order.getProducts().stream().map(ProductDTO::new).collect(Collectors.toList()));
        this.orderTime = order.getCreatedTime();
        this.totalPrice = order.getTotalPrice();
    }
    
}
