package com.brunomatias.autoflex.services;

import com.brunomatias.autoflex.dtos.ProductRawMaterialRequestDTO;
import com.brunomatias.autoflex.dtos.ProductRawMaterialResponseDTO;
import com.brunomatias.autoflex.mapper.ProductRawMaterialMapper;
import com.brunomatias.autoflex.models.Product;
import com.brunomatias.autoflex.models.ProductRawMaterial;
import com.brunomatias.autoflex.models.RawMaterial;
import com.brunomatias.autoflex.repositories.ProductRawMaterialRepository;
import com.brunomatias.autoflex.repositories.ProductRepository;
import com.brunomatias.autoflex.repositories.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductRawMaterialService {

    @Autowired
    private ProductRawMaterialRepository productRawMaterialRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    public List<ProductRawMaterialResponseDTO> findAll() {
        return productRawMaterialRepository.findAll(Sort.by("productRawMaterialId"))
                .stream()
                .map(ProductRawMaterialMapper::toResponseDTO)
                .toList();
    }

    public ProductRawMaterialResponseDTO findById(Long id) {
        ProductRawMaterial productRawMaterial = productRawMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductRawMaterial not found"));

        return ProductRawMaterialMapper.toResponseDTO(productRawMaterial);
    }

    public ProductRawMaterialResponseDTO save(ProductRawMaterialRequestDTO dto) {
        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        RawMaterial rawMaterial = rawMaterialRepository.findById(dto.rawMaterialId())
                .orElseThrow(() -> new RuntimeException("Raw material not found"));

        ProductRawMaterial entity = new ProductRawMaterial();
        entity.setProduct(product);
        entity.setRawMaterial(rawMaterial);
        entity.setRequiredQuantity(dto.requiredQuantity());

        ProductRawMaterial saved = productRawMaterialRepository.save(entity);

        return ProductRawMaterialMapper.toResponseDTO(saved);
    }

    public ProductRawMaterialResponseDTO update(Long id, ProductRawMaterialRequestDTO dto) {
        ProductRawMaterial existing = productRawMaterialRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("ProductRawMaterial not found with id: " + id)
                );

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found with id: " + dto.productId())
                );

        RawMaterial rawMaterial = rawMaterialRepository.findById(dto.rawMaterialId())
                .orElseThrow(() ->
                        new RuntimeException("RawMaterial not found with id: " + dto.rawMaterialId())
                );

        existing.setProduct(product);
        existing.setRawMaterial(rawMaterial);
        existing.setRequiredQuantity(dto.requiredQuantity());

        ProductRawMaterial updated = productRawMaterialRepository.save(existing);

        return ProductRawMaterialMapper.toResponseDTO(updated);
    }

    public void deleteProductRawMaterial(Long id) {
        productRawMaterialRepository.deleteById(id);
    }

    public List<ProductRawMaterialResponseDTO> findByProductId(Long idProduct) {
        return productRawMaterialRepository.findByProductId(idProduct)
                .stream()
                .map(ProductRawMaterialMapper::toResponseDTO)
                .toList();
    }
}