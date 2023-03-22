package com.example.productservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product extends AuditableEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;
}
