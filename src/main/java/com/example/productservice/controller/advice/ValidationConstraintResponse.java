package com.example.productservice.controller.advice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor(staticName = "of")
class ValidationConstraintResponse {

    private final List<FieldConstraintErrorEntry> validationConstraints;
}
