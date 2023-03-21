package com.example.productservice.service;

import com.example.productservice.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PageableProductService {
    Page<Product> findAll(Pageable pageable);
}
