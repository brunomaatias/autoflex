package com.brunomatias.autoflex.mapper;

import com.brunomatias.autoflex.dtos.ProductRawMaterialRequestDTO;
import com.brunomatias.autoflex.dtos.ProductRawMaterialResponseDTO;
import com.brunomatias.autoflex.models.Product;
import com.brunomatias.autoflex.models.ProductRawMaterial;
import com.brunomatias.autoflex.models.RawMaterial;

public class ProductRawMaterialMapper {

    public static ProductRawMaterialResponseDTO toResponseDTO(ProductRawMaterial entity) {
        return new ProductRawMaterialResponseDTO(
                entity.getProductRawMaterialId(),
                entity.getProduct().getProductId(),
                entity.getProduct().getName(),
                entity.getRawMaterial().getRawMaterialId(),
                entity.getRawMaterial().getName(),
                entity.getRequiredQuantity()
        );
    }

    public static ProductRawMaterial toEntity(ProductRawMaterialRequestDTO dto, Product product, RawMaterial rawMaterial) {
        ProductRawMaterial entity = new ProductRawMaterial();

        entity.setProduct(product);
        entity.setRawMaterial(rawMaterial);
        entity.setRequiredQuantity(dto.requiredQuantity());

        return entity;
    }

    public static void updateEntity(
            ProductRawMaterial entity,
            ProductRawMaterialRequestDTO dto,
            Product product,
            RawMaterial rawMaterial
    ) {
        entity.setProduct(product);
        entity.setRawMaterial(rawMaterial);
        entity.setRequiredQuantity(dto.requiredQuantity());
    }
}