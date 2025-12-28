package com.smartretailpriceengine.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartretailpriceengine.dto.ProductPriceDTO;
import com.smartretailpriceengine.exception.OutOfStockException;
import com.smartretailpriceengine.exception.ProductNotFoundException;
import com.smartretailpriceengine.service.PriceEngineService;

@RestController
@RequestMapping("/api/price/")
public class PriceEngineController {

	private final PriceEngineService engineService;

	public PriceEngineController(PriceEngineService engineService) {
		this.engineService = engineService;
	}

	@PostMapping("compute")
	public ResponseEntity<ProductPriceDTO> compute(@RequestParam("productId") int productId,
			@RequestParam("quantity") int quantity) throws ProductNotFoundException, OutOfStockException {
		return ResponseEntity.ok(engineService.compute(productId, quantity));
	}
}
