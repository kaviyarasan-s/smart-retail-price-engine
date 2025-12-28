package com.smartretailpriceengine.processor;

import com.smartretailpriceengine.entity.ProductPrice;

public interface RuleProcessor {

	public double compute(ProductPrice productPrice, String conditionJson, double value);
}
