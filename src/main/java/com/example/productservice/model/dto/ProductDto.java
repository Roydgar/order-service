package com.example.productservice.model.dto;

import com.example.productservice.model.entity.ProductType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import org.springframework.hateoas.RepresentationModel;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Getter
public class ProductDto extends RepresentationModel<ProductDto> {

    private UUID id;
    private String productName;
    private ProductType productType;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private int version;
}
