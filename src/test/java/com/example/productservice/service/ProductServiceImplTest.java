package com.example.productservice.service;

import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.model.entity.Product;
import com.example.productservice.repository.PageableProductRepository;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    private static final UUID PRODUCT_ID = UUID.fromString("e654542f-ba44-4d5b-898c-1f174183ce0a");

    @Mock
    private ProductRepository productRepository;
    @Mock
    private PageableProductRepository pageableProductRepository;
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl subject;

    @Test
    void findById() {
        Product expectedProduct = new Product();

        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(expectedProduct));

        Product actualProduct = subject.findById(PRODUCT_ID);

        verify(productRepository).findById(PRODUCT_ID);
        verifyNoMoreInteractions(productRepository);

        assertThat(actualProduct).isEqualTo(expectedProduct);
    }

    @Test
    void findById_whenProductIsNotFoundById_throwsProductNotFoundException() {
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class,
                () -> subject.findById(PRODUCT_ID));

        verify(productRepository).findById(PRODUCT_ID);
        verifyNoMoreInteractions(productRepository);

        String expectedMessage = "Product with id " + PRODUCT_ID + "is not found";
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}