package com.motoponk.assignment.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

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
@Audited
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Product extends BaseEntity {
    
    @Column(unique = true, nullable = false)
    @NotEmpty
    private String sku;
    
    @Column(nullable = false)
    @NotEmpty
    private String name;
    
    @Column(nullable = false)
    private BigDecimal price;
    
}
