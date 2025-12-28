package com.smartretailpriceengine.dto;

public record PricingRuleDTO(long id, String name, String ruleType, double value, int priority, String conditionJson,
		String isActive) {

}
