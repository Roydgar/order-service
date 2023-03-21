package com.example.productservice.model.dto;

import com.example.productservice.model.entity.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateProductRequest {
    @NotNull
    private UUID id;
    @NotBlank
    private String productName;
    @NotNull
    private ProductType productType;
}
