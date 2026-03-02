package com.brunomatias.autoflex.services;

import com.brunomatias.autoflex.dtos.ProductRawMaterialRequestDTO;
import com.brunomatias.autoflex.models.Product;
import com.brunomatias.autoflex.models.ProductRawMaterial;
import com.brunomatias.autoflex.models.RawMaterial;
import com.brunomatias.autoflex.repositories.ProductRawMaterialRepository;
import com.brunomatias.autoflex.repositories.ProductRepository;
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
class ProductRawMaterialServiceTest {

    @Mock
    private ProductRawMaterialRepository repository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RawMaterialRepository rawMaterialRepository;

    @InjectMocks
    private ProductRawMaterialService service;

    @Test
    void shouldSaveRelation() {
        Product product = new Product();
        product.setProductId(1L);

        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setRawMaterialId(1L);

        ProductRawMaterial saved = new ProductRawMaterial();
        saved.setProduct(product);
        saved.setRawMaterial(rawMaterial);
        saved.setRequiredQuantity(5);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        when(rawMaterialRepository.findById(1L))
                .thenReturn(Optional.of(rawMaterial));

        when(repository.save(any(ProductRawMaterial.class)))
                .thenReturn(saved);

        var dto = new ProductRawMaterialRequestDTO(1L, 1L, 5);

        var response = service.save(dto);

        assertEquals(5, response.requiredQuantity());
        verify(repository).save(any(ProductRawMaterial.class));
    }
}
