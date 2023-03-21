package com.example.productservice.repository;

import com.example.productservice.model.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PageableProductRepository extends PagingAndSortingRepository<Product, UUID> {
}
