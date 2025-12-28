package com.smartretailpriceengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartretailpriceengine.entity.Product;

@Repository
public interface ProductManagementRepository extends JpaRepository<Product, Long> {

}
