package com.example.productservice.model.dto;

import com.example.productservice.model.entity.ProductType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Builder
@Getter
public class ProductDto extends RepresentationModel<ProductDto> {

    private UUID id;
    private String productName;
    private ProductType productType;
}
