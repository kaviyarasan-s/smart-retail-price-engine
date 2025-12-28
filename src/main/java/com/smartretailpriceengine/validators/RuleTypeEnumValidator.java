package com.smartretailpriceengine.validators;

import java.util.stream.Stream;

import com.smartretailpriceengine.enums.RuleType;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RuleTypeEnumValidator implements ConstraintValidator<RuleTypeEnumValid, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return Stream.of(RuleType.values()).anyMatch(rt -> rt.toString().equals(value));
	}

}
