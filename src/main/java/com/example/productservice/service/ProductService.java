package com.example.productservice.service;

import com.example.productservice.model.dto.CreateProductRequest;
import com.example.productservice.model.dto.UpdateProductRequest;
import com.example.productservice.model.entity.Product;

import java.util.UUID;

public interface ProductService extends PageableProductService {
    Product findById(UUID id);

    Product create(CreateProductRequest createRequest);

    Product update(UpdateProductRequest updateRequest);

    void delete(UUID id);
}
