package com.example.productservice.service;

import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.model.dto.CreateProductRequest;
import com.example.productservice.model.dto.UpdateProductRequest;
import com.example.productservice.model.entity.Product;
import com.example.productservice.repository.PageableProductRepository;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final PageableProductRepository pageableProductRepository;
    private final ProductMapper productMapper;

    @Override
    public Product findById(UUID id) {
        log.debug("Looking for a product with id {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + "is not found"));

        log.info("Retrieved a product with id {}", id);
        return product;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        log.debug("Retrieving products. Page request: {}", pageable);

        Page<Product> products = pageableProductRepository.findAll(pageable);

        log.info("Retrieved {} products of {} total", products.getSize(), products.getTotalElements());
        return products;
    }

    @Override
    public Product create(CreateProductRequest createRequest) {
        log.debug("Creating a new product");
        Product product = productMapper.toProduct(createRequest);
        Product createdProduct = productRepository.save(product);

        log.info("Created a new product with id {}", createdProduct);
        return createdProduct;
    }

    @Override
    public Product update(UpdateProductRequest updateRequest) {
        log.debug("Updating a product with id {}", updateRequest.getId());
        Product product = findById(updateRequest.getId());

        product.setProductName(updateRequest.getProductName());
        product.setProductType(updateRequest.getProductType());

        Product updatedProduct = productRepository.save(product);

        log.info("Updated a product with id {}", updatedProduct.getId());
        return updatedProduct;
    }

    @Override
    public void delete(UUID id) {
        log.debug("Deleting product with id {}", id);
        Product product = findById(id);

        productRepository.delete(product);
        log.info("Product with id {} is deleted", product.getId());
    }
}
