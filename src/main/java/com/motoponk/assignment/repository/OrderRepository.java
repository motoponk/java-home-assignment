package com.motoponk.assignment.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoponk.assignment.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCreatedTimeGreaterThanEqualAndCreatedTimeLessThanEqual(
            LocalDateTime startDate, LocalDateTime endDate);

}
