package com.brunomatias.autoflex.services;

import com.brunomatias.autoflex.dtos.RawMaterialRequestDTO;
import com.brunomatias.autoflex.models.RawMaterial;
import com.brunomatias.autoflex.repositories.RawMaterialRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RawMaterialServiceTest {

    @Mock
    private RawMaterialRepository repository;

    @InjectMocks
    private RawMaterialService service;

    @Test
    void shouldFindById() {
        RawMaterial material = new RawMaterial();
        material.setRawMaterialId(1L);
        material.setName("Steel");

        when(repository.findById(1L)).thenReturn(Optional.of(material));

        var response = service.findById(1L);

        assertEquals("Steel", response.name());
    }

    @Test
    void shouldSaveRawMaterial() {
        RawMaterialRequestDTO dto =
                new RawMaterialRequestDTO(null, "R001", "Steel", 50);

        RawMaterial entity = new RawMaterial();
        entity.setRawMaterialId(1L);
        entity.setName("Steel");
        entity.setStockQuantity(50);

        when(repository.save(any(RawMaterial.class))).thenReturn(entity);

        var response = service.save(dto);

        assertEquals(50, response.stockQuantity());
        verify(repository).save(any(RawMaterial.class));
    }
}
