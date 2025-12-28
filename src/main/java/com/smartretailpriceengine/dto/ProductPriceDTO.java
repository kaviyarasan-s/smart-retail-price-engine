package com.smartretailpriceengine.dto;

import java.util.List;

import com.smartretailpriceengine.enums.RuleType;

public record ProductPriceDTO(double basePrice, List<RuleType> appliedRuleTypes, double finalComputedPrice) {

}
