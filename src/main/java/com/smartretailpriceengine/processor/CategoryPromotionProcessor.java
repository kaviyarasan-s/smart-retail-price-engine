package com.smartretailpriceengine.processor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smartretailpriceengine.entity.ProductPrice;
import com.smartretailpriceengine.enums.RuleType;

public class CategoryPromotionProcessor implements RuleProcessor {

	@Override
	public double compute(ProductPrice productPrice, String conditionJson, double value) {
		JsonObject conditionJsonObj = JsonParser.parseString(conditionJson).getAsJsonObject();
		double finalPrice = 0;
		if (conditionJsonObj != null
				&& productPrice.getCategory().equals(conditionJsonObj.get("category").getAsString())) {
			// Applying the discount if the product category matches with condition
			// Here value is no.of percentage is need to subtract if the product category is
			// match
			finalPrice = productPrice.getFinalComputedPrice() - (productPrice.getFinalComputedPrice() * (value / 100));
			productPrice.getAppliedRuleTypes().add(RuleType.CATEGORY_PROMOTION);
			productPrice.setFinalComputedPrice(finalPrice);
		}
		return finalPrice;
	}

}
