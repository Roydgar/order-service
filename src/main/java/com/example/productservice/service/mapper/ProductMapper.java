package com.example.productservice.service.mapper;

import com.example.productservice.model.dto.CreateProductRequest;
import com.example.productservice.model.dto.ProductDto;
import com.example.productservice.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(CreateProductRequest createRequest) {
        return Product.builder()
                .productName(createRequest.getProductName())
                .productType(createRequest.getProductType())
                .build();

    }

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productType(product.getProductType())
                .build();
    }
}
