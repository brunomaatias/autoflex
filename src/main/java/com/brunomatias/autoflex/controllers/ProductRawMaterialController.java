package com.brunomatias.autoflex.controllers;

import com.brunomatias.autoflex.dtos.ProductRawMaterialRequestDTO;
import com.brunomatias.autoflex.dtos.ProductRawMaterialResponseDTO;
import com.brunomatias.autoflex.services.ProductRawMaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-raw-materials")
public class ProductRawMaterialController {

    @Autowired
    private ProductRawMaterialService productRawMaterialService;

    @GetMapping
    public ResponseEntity<List<ProductRawMaterialResponseDTO>> getAllProductRawMaterials() {
        return ResponseEntity.ok(productRawMaterialService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRawMaterialResponseDTO> getByIdProductRawMaterial(@PathVariable Long id) {
        return ResponseEntity.ok(productRawMaterialService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductRawMaterialResponseDTO> createProductRawMaterial(@RequestBody @Valid ProductRawMaterialRequestDTO dto) {
        ProductRawMaterialResponseDTO created = productRawMaterialService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRawMaterialResponseDTO> updateProductRawMaterial(
            @PathVariable Long id,
            @RequestBody @Valid ProductRawMaterialRequestDTO dto) {

        ProductRawMaterialResponseDTO updated = productRawMaterialService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductRawMaterial(@PathVariable Long id) {
        productRawMaterialService.deleteProductRawMaterial(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/product/{idProduct}")
    public ResponseEntity<List<ProductRawMaterialResponseDTO>> getAllProductRawMaterialsByIdProduct(@PathVariable Long idProduct) {
        List<ProductRawMaterialResponseDTO> teste = productRawMaterialService.findByProductId(idProduct);
        return ResponseEntity.ok(productRawMaterialService.findByProductId(idProduct));
    }
}