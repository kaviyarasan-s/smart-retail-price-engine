package com.smartretailpriceengine.enums;

public enum RuleType {

	PERCENTAGE_DISCOUNT {
		@Override
		public String toString() {
			return "percentage discount";
		}
	},

	FLAT_DISCOUNT {
		@Override
		public String toString() {
			return "flat discount";
		}
	},
	BULK_PRICING {
		@Override
		public String toString() {
			return "bulk pricing";
		}
	},
	CATEGORY_PROMOTION {
		@Override
		public String toString() {
			return "category promotion";
		}
	};

	public static RuleType fromString(String value) {
		for (RuleType type : RuleType.values()) {
			if (type.toString().equalsIgnoreCase(value)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid RuleType: " + value);
	}
}
