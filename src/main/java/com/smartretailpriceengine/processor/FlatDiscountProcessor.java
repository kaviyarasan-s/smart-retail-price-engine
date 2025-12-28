package com.smartretailpriceengine.processor;

import com.smartretailpriceengine.entity.ProductPrice;
import com.smartretailpriceengine.enums.RuleType;

public class FlatDiscountProcessor implements RuleProcessor {

	@Override
	public double compute(ProductPrice productPrice, String conditionJson, double value) {
		// Here value is amount that subtract form the original price
		double finalPrice = productPrice.getFinalComputedPrice() - value;
		productPrice.getAppliedRuleTypes().add(RuleType.FLAT_DISCOUNT);
		productPrice.setFinalComputedPrice(finalPrice);
		return finalPrice;
	}

}
