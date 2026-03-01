package com.brunomatias.autoflex.mapper;

import com.brunomatias.autoflex.dtos.RawMaterialRequestDTO;
import com.brunomatias.autoflex.dtos.RawMaterialResponseDTO;
import com.brunomatias.autoflex.models.RawMaterial;

public class RawMaterialMapper {
    public static RawMaterial toEntity(RawMaterialRequestDTO dto) {
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setRawMaterialId(dto.rawMaterialId());
        rawMaterial.setCode(dto.code());
        rawMaterial.setName(dto.name());
        rawMaterial.setStockQuantity(dto.stockQuantity());

        return rawMaterial;
    }

    public static RawMaterialResponseDTO toResponseDTO(RawMaterial rawMaterial) {
        return new RawMaterialResponseDTO(
                rawMaterial.getRawMaterialId(),
                rawMaterial.getCode(),
                rawMaterial.getName(),
                rawMaterial.getStockQuantity()
        );
    }
}