package com.smartretailpriceengine.processor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smartretailpriceengine.entity.ProductPrice;
import com.smartretailpriceengine.enums.RuleType;

public class BulkPricingProcessor implements RuleProcessor {

	@Override
	public double compute(ProductPrice productPrice, String conditionJson, double value) {
		JsonObject conditionJsonObj = JsonParser.parseString(conditionJson).getAsJsonObject();
		double finalPrice = 0;
		if (conditionJsonObj != null && productPrice.getQuantity() >= conditionJsonObj.get("quantity").getAsInt()) {
			finalPrice = productPrice.getFinalComputedPrice() - (productPrice.getFinalComputedPrice() * (value / 100));
			productPrice.getAppliedRuleTypes().add(RuleType.BULK_PRICING);
			productPrice.setFinalComputedPrice(finalPrice);
		}
		return finalPrice;
	}

}
