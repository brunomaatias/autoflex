package com.brunomatias.autoflex.dtos;

import java.math.BigDecimal;

public record ProductionSuggestionDTO(
        Long productId,
        String productName,
        int quantityPossible,
        BigDecimal unitPrice,
        BigDecimal totalValue
) {}
