package com.smartretailpriceengine.entity;

import java.math.BigDecimal;

import com.smartretailpriceengine.enums.RuleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pricing_rules")
public class PricingRule {

	@Id
	@TableGenerator(initialValue = 0, valueColumnName = "next_val", pkColumnName = "seq_name", pkColumnValue = "pricing_rules", table = "pricing_rules_id_seq", name = "pricing_rules_id_seq_gen")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "pricing_rules_id_seq_gen")
	private long id;

	@NotNull(message = "Pricing Rule name should not be null")
	@NotEmpty(message = "Pricing Rule name should not be empty")
	@Column(length = 500)
	private String name;

	@Enumerated
	// @RuleTypeEnumValid(message = "Rule type should be in {percentage discount,
	// flat discount, bulk pricing, category promotion}")
	private RuleType ruleType;

	@Min(value = 0, message = "Value should not be negative")
	@Column(precision = 10, scale = 2)
	private BigDecimal value;

	@Min(value = 0, message = "Priority should not be negative")
	private int priority;

	private String conditionJson;

	@NotNull(message = "Is active should not be null")
	@NotEmpty(message = "Is active should not be empty")
	@Column(length = 1)
	private String isActive;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getConditionJson() {
		return conditionJson;
	}

	public void setConditionJson(String conditionJson) {
		this.conditionJson = conditionJson;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}
