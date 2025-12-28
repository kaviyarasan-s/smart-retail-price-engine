package com.smartretailpriceengine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.smartretailpriceengine.dto.PricingRuleDTO;
import com.smartretailpriceengine.entity.PricingRule;
import com.smartretailpriceengine.exception.PricingRuleNotFoundException;
import com.smartretailpriceengine.repository.PricingRulesRepository;

@Service
public class PricingRulesService {

	private final PricingRulesRepository repository;

	public PricingRulesService(PricingRulesRepository repository) {
		this.repository = repository;
	}

	public PricingRuleDTO find(long ruleId) throws PricingRuleNotFoundException {
		PricingRule pricingRule = findById(ruleId);
		return transformPricingRuleToPricingRuleDTO(pricingRule);
	}

	private PricingRule findById(long ruleId) throws PricingRuleNotFoundException {
		return repository.findById(ruleId).orElseThrow(
				() -> new PricingRuleNotFoundException("Pricing rule is not found for the id : " + ruleId));
	}

	private PricingRuleDTO transformPricingRuleToPricingRuleDTO(PricingRule pricingRule) {
		return new PricingRuleDTO(pricingRule.getId(), pricingRule.getName(), pricingRule.getRuleType().toString(),
				pricingRule.getValue().doubleValue(), pricingRule.getPriority(), pricingRule.getConditionJson(),
				pricingRule.getIsActive());
	}

	public List<PricingRuleDTO> findAll() {
		List<PricingRule> pricingRules = repository.findAll(Sort.by(Order.asc("priority")));
		List<PricingRuleDTO> pricingRuleDTOs = new ArrayList<>();
		for (PricingRule pricingRule : pricingRules) {
			pricingRuleDTOs.add(transformPricingRuleToPricingRuleDTO(pricingRule));
		}
		return pricingRuleDTOs;
	}

	public PricingRuleDTO save(PricingRule pricingRule) {
		PricingRule persistedPricingRule = repository.save(pricingRule);
		return transformPricingRuleToPricingRuleDTO(persistedPricingRule);
	}

	public PricingRuleDTO update(long ruleId, PricingRule updatePricingRule) throws PricingRuleNotFoundException {
		PricingRule pricingRule = findById(ruleId);
		pricingRule.setName(updatePricingRule.getName());
		pricingRule.setRuleType(updatePricingRule.getRuleType());
		pricingRule.setValue(updatePricingRule.getValue());
		pricingRule.setPriority(updatePricingRule.getPriority());
		pricingRule.setConditionJson(updatePricingRule.getConditionJson());
		pricingRule.setIsActive(updatePricingRule.getIsActive());
		PricingRule persistedPricingRule = repository.save(pricingRule);
		return transformPricingRuleToPricingRuleDTO(persistedPricingRule);
	}

	public void delete(long ruleId) throws PricingRuleNotFoundException {
		PricingRule pricingRule = findById(ruleId);
		if (pricingRule != null) {
			repository.deleteById(ruleId);
		}
	}
}
