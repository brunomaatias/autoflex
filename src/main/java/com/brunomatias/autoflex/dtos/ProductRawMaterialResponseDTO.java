package com.brunomatias.autoflex.dtos;

import jakarta.validation.constraints.Positive;

public record ProductRawMaterialResponseDTO(
        Long productRawMaterialId,
        Long productId,
        String productName,
        Long rawMaterialId,
        String rawMaterialName,

        @Positive
        Integer requiredQuantity
) {}
