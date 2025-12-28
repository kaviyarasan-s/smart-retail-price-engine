package com.smartretailpriceengine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartretailpriceengine.dto.ProductDTO;
import com.smartretailpriceengine.entity.Product;
import com.smartretailpriceengine.exception.ProductNotFoundException;
import com.smartretailpriceengine.repository.ProductManagementRepository;
import com.smartretailpriceengine.util.Utility;

@Service
public class ProductManagementService {

	private final ProductManagementRepository repository;

	public ProductManagementService(ProductManagementRepository repository) {
		this.repository = repository;
	}

	public ProductDTO find(long productId) throws ProductNotFoundException {
		Product product = findById(productId);
		return transformProductObjectToDTO(product);
	}

	private Product findById(long productId) throws ProductNotFoundException {
		return repository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product is not found for the id : " + productId));
	}

	public List<ProductDTO> findAll() {
		List<Product> products = repository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (Product product : products) {
			ProductDTO productDTO = transformProductObjectToDTO(product);
			productDTOs.add(productDTO);
		}
		return productDTOs;

	}

	public ProductDTO save(Product product) {
		product.setCreatedOn(System.currentTimeMillis());
		product.setModifiedOn(System.currentTimeMillis());
		Product persistedProduct = repository.save(product);
		return transformProductObjectToDTO(persistedProduct);
	}

	public ProductDTO update(long productId, Product updateProduct) throws ProductNotFoundException {
		Product product = findById(productId);
		product.setName(updateProduct.getName());
		product.setBasePrice(updateProduct.getBasePrice());
		product.setCategory(updateProduct.getCategory());
		product.setBrand(updateProduct.getBrand());
		product.setStockQuantity(updateProduct.getStockQuantity());
		product.setModifiedOn(System.currentTimeMillis());
		Product persistedProduct = repository.save(product);
		return transformProductObjectToDTO(persistedProduct);
	}

	public void delete(long productId) throws ProductNotFoundException {
		Product product = findById(productId);
		if (product != null) {
			repository.deleteById(productId);
		}
	}

	private ProductDTO transformProductObjectToDTO(Product product) {
		return new ProductDTO(product.getId(), product.getName(), product.getBasePrice().doubleValue(),
				product.getCategory(), product.getBrand(), product.getStockQuantity(),
				Utility.convertMillisToTimeStamp(product.getCreatedOn()),
				Utility.convertMillisToTimeStamp(product.getModifiedOn()));
	}
}
