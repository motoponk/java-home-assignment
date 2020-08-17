package com.motoponk.assignment.model.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    @Column(nullable = false)
    @NotEmpty
    @Email
    private String email;
    
    @ManyToMany(targetEntity = Product.class, 
            cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();
    
    private BigDecimal totalPrice;
    
}
