package com.example.productservice.service;

import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.model.dto.CreateProductRequest;
import com.example.productservice.model.dto.UpdateProductRequest;
import com.example.productservice.model.entity.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.mapper.ProductMapper;
import com.example.productservice.util.DateTimeProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final DateTimeProvider dateTimeProvider;

    @Override
    public Product findById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + "is not found"));
    }

    @Override
    public Product create(CreateProductRequest createRequest) {
        Product product = productMapper.toProduct(createRequest);

        ZonedDateTime nowDateTime = dateTimeProvider.nowUTC();
        product.setCreatedAt(nowDateTime);
        product.setUpdatedAt(nowDateTime);

        return productRepository.save(product);
    }

    @Override
    public Product update(UpdateProductRequest updateRequest) {
        ZonedDateTime nowDateTime = dateTimeProvider.nowUTC();

        Product product = findById(updateRequest.getId());

        product.setProductName(updateRequest.getProductName());
        product.setProductType(updateRequest.getProductType());
        product.setUpdatedAt(nowDateTime);

        return productRepository.save(product);
    }

    @Override
    public void delete(UUID id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}
