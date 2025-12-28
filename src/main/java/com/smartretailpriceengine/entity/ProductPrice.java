package com.smartretailpriceengine.entity;

import java.util.List;

import com.smartretailpriceengine.enums.RuleType;

public class ProductPrice {

	private double basePrice;
	private List<RuleType> appliedRuleTypes;
	private double finalComputedPrice;

	private int quantity;
	private String category;

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public List<RuleType> getAppliedRuleTypes() {
		return appliedRuleTypes;
	}

	public void setAppliedRuleTypes(List<RuleType> appliedRuleTypes) {
		this.appliedRuleTypes = appliedRuleTypes;
	}

	public double getFinalComputedPrice() {
		return finalComputedPrice;
	}

	public void setFinalComputedPrice(double finalComputedPrice) {
		this.finalComputedPrice = finalComputedPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
