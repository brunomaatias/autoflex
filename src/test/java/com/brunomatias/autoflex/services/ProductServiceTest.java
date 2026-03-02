package com.brunomatias.autoflex.services;

import com.brunomatias.autoflex.dtos.ProductRequestDTO;
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

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RawMaterialRepository rawMaterialRepository;

    @Mock
    private ProductRawMaterialRepository productRawMaterialRepository;

    @InjectMocks
    private ProductService service;

    @Test
    void shouldReturnAllProducts() {
        Product product = new Product();
        product.setProductId(1L);
        product.setName("Product A");
        product.setPrice(BigDecimal.TEN);

        when(productRepository.findAll()).thenReturn(List.of(product));

        var result = service.findAll();

        assertEquals(1, result.size());
        assertEquals("Product A", result.get(0).name());
        verify(productRepository).findAll();
    }

    @Test
    void shouldSaveProduct() {
        ProductRequestDTO dto =
                new ProductRequestDTO(null, "P001", "Product A", BigDecimal.TEN);

        Product entity = new Product();
        entity.setProductId(1L);
        entity.setCode("P001");
        entity.setName("Product A");
        entity.setPrice(BigDecimal.TEN);

        when(productRepository.save(any(Product.class))).thenReturn(entity);

        var response = service.save(dto);

        assertEquals("Product A", response.name());
        assertEquals(BigDecimal.TEN, response.price());
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void shouldDeleteProduct() {
        service.deleteProductById(1L);
        verify(productRepository).deleteById(1L);
    }

    @Test
    void shouldCalculateProductionPlanCorrectly() {
        Product product = new Product();
        product.setProductId(1L);
        product.setName("Product A");
        product.setPrice(BigDecimal.valueOf(10));

        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setRawMaterialId(1L);
        rawMaterial.setStockQuantity(100);

        ProductRawMaterial relation = new ProductRawMaterial();
        relation.setProduct(product);
        relation.setRawMaterial(rawMaterial);
        relation.setRequiredQuantity(5);

        when(productRepository.findAllByOrderByPriceDesc())
                .thenReturn(List.of(product));

        when(rawMaterialRepository.findAll())
                .thenReturn(List.of(rawMaterial));

        when(productRawMaterialRepository.findByProductId(1L))
                .thenReturn(List.of(relation));

        var result = service.calculateProductionPlan();

        assertEquals(1, result.size());
        assertEquals(20, result.get(0).quantityPossible());
        assertEquals(BigDecimal.valueOf(200), result.get(0).totalValue());
    }
}