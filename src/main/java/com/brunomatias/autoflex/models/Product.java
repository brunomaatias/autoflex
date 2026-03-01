package com.brunomatias.autoflex.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @PrePersist
    protected void onCreate() {
        if (this.code == null || this.code.trim().isEmpty()) {
            this.code = "PRD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }
}