package com.example.productservice.controller;

import com.example.productservice.controller.advice.ApplicationControllerAdvice;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.model.dto.ProductDto;
import com.example.productservice.model.entity.Product;
import com.example.productservice.model.entity.ProductType;
import com.example.productservice.service.ProductService;
import com.example.productservice.service.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    private static final UUID PRODUCT_ID = UUID.fromString("e654542f-ba44-4d5b-898c-1f174183ce0a");

    @Mock
    private ProductService productService;
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductController subject;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(subject)
                .setControllerAdvice(new ApplicationControllerAdvice())
                .build();
    }

    @Test
    void getById() throws Exception {
        Product product = new Product();
        ProductDto productDto = ProductDto.builder()
                .id(PRODUCT_ID)
                .productName("myProduct")
                .productType(ProductType.PHONE)
                .build();

        when(productService.findById(any(UUID.class))).thenReturn(product);
        when(productMapper.toDto(any(Product.class))).thenReturn(productDto);

        mockMvc.perform(
                        get("/api/v1/products/{id}", PRODUCT_ID)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productDto.getId().toString()))
                .andExpect(jsonPath("$.productName").value(productDto.getProductName()))
                .andExpect(jsonPath("$.productType").value(productDto.getProductType().toString()));

        verify(productService).findById(PRODUCT_ID);
        verify(productMapper).toDto(product);
        verifyNoMoreInteractions(productService, productMapper);
    }

    @Test
    void getById_whenProductNotFoundExceptionIsThrows_returns404() throws Exception {
        when(productService.findById(any(UUID.class))).thenThrow(new ProductNotFoundException("Product not found"));

        mockMvc.perform(
                        get("/api/v1/products/{id}", PRODUCT_ID)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value("Product not found"));

        verify(productService).findById(PRODUCT_ID);
        verifyNoInteractions(productMapper);
        verifyNoMoreInteractions(productService);
    }

    @Test
    void getProducts() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteById() {
    }
}