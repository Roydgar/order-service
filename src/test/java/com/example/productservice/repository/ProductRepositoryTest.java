package com.example.productservice.repository;

import com.example.productservice.ProductServiceApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

// Heavy integration test with spring context
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProductServiceApplication.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void context() {
        assertThat(productRepository).isNotNull();
    }
}