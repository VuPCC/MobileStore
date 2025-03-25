package com.mobilestore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double unitPrice;
    private Integer unitInStock;
    private String description;
    private String manufacturer;
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Condition productCondition;

    public enum Condition {
        NEW, OLD, REFURBISHED
    }
}
