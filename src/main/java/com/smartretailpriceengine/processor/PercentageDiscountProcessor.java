package com.smartretailpriceengine.processor;

import com.smartretailpriceengine.entity.ProductPrice;
import com.smartretailpriceengine.enums.RuleType;

public class PercentageDiscountProcessor implements RuleProcessor {

	@Override
	public double compute(ProductPrice productPrice, String conditionJson, double value) {
		double finalPrice = 0;
		// Here value is considered as 10% or 20%
		// subtracting 10% or 20% from the overall price
		finalPrice = productPrice.getFinalComputedPrice() - (productPrice.getFinalComputedPrice() * (value / 100));
		productPrice.getAppliedRuleTypes().add(RuleType.PERCENTAGE_DISCOUNT);
		productPrice.setFinalComputedPrice(finalPrice);
		return finalPrice;
	}

}
