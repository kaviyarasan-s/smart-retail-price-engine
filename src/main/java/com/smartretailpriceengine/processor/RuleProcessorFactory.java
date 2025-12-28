package com.smartretailpriceengine.processor;

import com.smartretailpriceengine.enums.RuleType;

public final class RuleProcessorFactory {

	private RuleProcessorFactory() {
	}

	public static RuleProcessor getRuleProcessor(RuleType ruletype) {
		return switch (ruletype) {
		case PERCENTAGE_DISCOUNT -> new PercentageDiscountProcessor();
		case FLAT_DISCOUNT -> new FlatDiscountProcessor();
		case BULK_PRICING -> new BulkPricingProcessor();
		case CATEGORY_PROMOTION -> new CategoryPromotionProcessor();
		};
	}
}
