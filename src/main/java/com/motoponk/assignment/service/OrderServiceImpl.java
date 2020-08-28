package com.motoponk.assignment.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.motoponk.assignment.exception.NoSuchProductException;
import com.motoponk.assignment.model.dto.OrderDTO;
import com.motoponk.assignment.model.dto.OrderRequestDTO;
import com.motoponk.assignment.model.dto.ProductDTO;
import com.motoponk.assignment.model.entity.Order;
import com.motoponk.assignment.model.entity.Product;
import com.motoponk.assignment.repository.OrderRepository;
import com.motoponk.assignment.repository.ProductRepository;

@RequiredArgsConstructor
@Service
@Transactional
class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderDTO saveOrder(OrderRequestDTO orderDTO) {
        Set<Product> products = readProducts(orderDTO);
        BigDecimal totalPrice = calculateTotalPrice(products);
        
        Order order = new Order(orderDTO.getEmail(), products, totalPrice);
        Order savedOrder = orderRepository.save(order);
        return createResult(savedOrder);
    }

    private Set<Product> readProducts(OrderRequestDTO orderDTO) {
        return orderDTO.getProducts().stream()
                .map(this::readProduct)
                .collect(Collectors.toSet());
    }
    

    private Product readProduct(ProductDTO productDTO) {
        // TODO This can be used from ProductService and can be cached.
        return productRepository.findBySku(productDTO.getSku())
                                .orElseThrow(() -> new NoSuchProductException(productDTO.getSku()));
    }

    private OrderDTO createResult(Order order) {
        OrderDTO orderDTO = OrderDTO.builder()
                                    .orderTime(order.getCreatedTime())
                                    .totalPrice(order.getTotalPrice())
                                    .build();
        orderDTO.setEmail(order.getEmail());
        orderDTO.setProducts(order.getProducts().stream().map(ProductDTO::new).collect(Collectors.toList()));
        return orderDTO;
    }

    private BigDecimal calculateTotalPrice(Set<Product> products) {
        double total = products.stream().mapToDouble(e -> e.getPrice().doubleValue()).sum();
        return BigDecimal.valueOf(total);
    }
    
    @Override
    public List<OrderDTO> retreiveOrders(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findAllByCreatedTimeGreaterThanEqualAndCreatedTimeLessThanEqual(startDate, endDate)
                              .stream()
                              .map(OrderDTO::new)
                              .collect(Collectors.toList());
    }
    
}
