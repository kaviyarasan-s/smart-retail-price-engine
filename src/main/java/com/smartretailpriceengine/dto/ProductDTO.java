package com.smartretailpriceengine.dto;

public record ProductDTO(long id, String name, double basePrice, String category, String brand, int stockQuantity,
		String createdOn, String modifiedOn) {

}
