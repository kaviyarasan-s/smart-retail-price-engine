package com.smartretailpriceengine.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartretailpriceengine.dto.ProductDTO;
import com.smartretailpriceengine.entity.Product;
import com.smartretailpriceengine.exception.ProductNotFoundException;
import com.smartretailpriceengine.service.ProductManagementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products/")
public class ProductManagementController {

	private final ProductManagementService service;

	public ProductManagementController(ProductManagementService service) {
		this.service = service;
	}

	@GetMapping("find/{productid}")
	public ResponseEntity<ProductDTO> find(@PathVariable("productid") long productId) throws ProductNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(service.find(productId));
	}

	@GetMapping("findall")
	public ResponseEntity<List<ProductDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@PostMapping("add")
	public ResponseEntity<ProductDTO> save(@Valid @RequestBody Product product) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
	}

	@PutMapping("update/{productid}")
	public ResponseEntity<ProductDTO> update(@Valid @PathVariable("productid") long productId,
			@RequestBody Product product) throws ProductNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(productId, product));
	}

	@DeleteMapping("delete/{productid}")
	public HeadersBuilder<?> delete(@PathVariable("productid") long productId) throws ProductNotFoundException {
		service.delete(productId);
		return ResponseEntity.noContent();
	}

}
