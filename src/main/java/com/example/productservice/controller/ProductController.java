package com.example.productservice.controller;

import com.example.productservice.model.dto.CreateProductRequest;
import com.example.productservice.model.dto.ProductDto;
import com.example.productservice.model.dto.UpdateProductRequest;
import com.example.productservice.model.entity.Product;
import com.example.productservice.service.ProductService;
import com.example.productservice.service.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable UUID id) {
        Product product = productService.findById(id);
        ProductDto productDto = productMapper.toDto(product);

        productDto.add(linkTo(methodOn(ProductController.class).getById(productDto.getId())).withSelfRel());
        return productDto;
    }
    @GetMapping
    public Page<ProductDto> getById(Pageable pageable) {
        Page<Product> products = productService.findAll(pageable);

        return products.map(productMapper::toDto);
    }


    @PostMapping
    @ResponseStatus(CREATED)
    public ProductDto createProduct(@RequestBody @Valid CreateProductRequest createRequest) {
        Product createdProduct = productService.create(createRequest);
        ProductDto productDto = productMapper.toDto(createdProduct);

        productDto.add(linkTo(methodOn(ProductController.class).getById(productDto.getId())).withSelfRel());
        return productDto;
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody @Valid UpdateProductRequest updateRequest) {
        Product updatedProduct = productService.update(updateRequest);
        ProductDto productDto = productMapper.toDto(updatedProduct);

        productDto.add(linkTo(methodOn(ProductController.class).getById(productDto.getId())).withSelfRel());
        return productDto;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        productService.delete(id);
    }
}
