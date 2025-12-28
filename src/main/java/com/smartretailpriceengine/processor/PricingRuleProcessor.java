package com.smartretailpriceengine.processor;

import com.smartretailpriceengine.entity.ProductPrice;

public class PricingRuleProcessor {

	private final RuleProcessor ruleProcessor;

	public PricingRuleProcessor(RuleProcessor ruleProcessor) {
		this.ruleProcessor = ruleProcessor;
	}

	public double calculatePrice(ProductPrice productPrice, String conditionJson, double value) {
		return ruleProcessor.compute(productPrice, conditionJson, value);
	}
}
