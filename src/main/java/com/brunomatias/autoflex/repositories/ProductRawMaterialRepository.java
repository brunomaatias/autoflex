package com.brunomatias.autoflex.repositories;

import com.brunomatias.autoflex.models.ProductRawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRawMaterialRepository extends JpaRepository<ProductRawMaterial, Long> {

    @Query("SELECT p FROM ProductRawMaterial p WHERE p.product.productId = :id")
    List<ProductRawMaterial> findByProductId(@Param("id") Long id);
}
