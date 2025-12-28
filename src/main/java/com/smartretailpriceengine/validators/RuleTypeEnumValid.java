package com.smartretailpriceengine.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = RuleTypeEnumValidator.class)
public @interface RuleTypeEnumValid {
	String message();

	Class<?>[] groups() default {}; // Required groups

	Class<? extends Payload>[] payload() default {}; // Required payload
}
