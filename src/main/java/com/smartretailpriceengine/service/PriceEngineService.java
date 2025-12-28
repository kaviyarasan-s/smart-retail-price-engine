package com.smartretailpriceengine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartretailpriceengine.dto.PricingRuleDTO;
import com.smartretailpriceengine.dto.ProductDTO;
import com.smartretailpriceengine.dto.ProductPriceDTO;
import com.smartretailpriceengine.entity.ProductPrice;
import com.smartretailpriceengine.enums.RuleType;
import com.smartretailpriceengine.exception.OutOfStockException;
import com.smartretailpriceengine.exception.ProductNotFoundException;
import com.smartretailpriceengine.processor.PricingRuleProcessor;
import com.smartretailpriceengine.processor.RuleProcessor;
import com.smartretailpriceengine.processor.RuleProcessorFactory;

@Service
public class PriceEngineService {

	private final ProductManagementService productManagementService;
	private final PricingRulesService pricingRulesService;

	public PriceEngineService(ProductManagementService productManagementService,
			PricingRulesService pricingRulesService) {
		this.productManagementService = productManagementService;
		this.pricingRulesService = pricingRulesService;
	}

	public ProductPriceDTO compute(long productId, int quantity) throws ProductNotFoundException, OutOfStockException {
		List<RuleType> appliedRuleTypes = new ArrayList<>();
		ProductPrice productPrice = new ProductPrice();
		productPrice.setAppliedRuleTypes(appliedRuleTypes);
		// fetch product base price
		ProductDTO productDTO = productManagementService.find(productId);
		if (productDTO.stockQuantity() < quantity) {
			throw new OutOfStockException(
					"Out of stock!!! Choosen product contains only " + productDTO.stockQuantity() + " items ");
		}
		// multiply with no. of quantity
		double totalPrice = productDTO.basePrice() * quantity;
		productPrice.setBasePrice(productDTO.basePrice());
		productPrice.setFinalComputedPrice(totalPrice);
		productPrice.setQuantity(quantity);
		productPrice.setCategory(productDTO.category());
		// fetch pricing rules ordered with priority
		List<PricingRuleDTO> pricingRuleDTOs = pricingRulesService.findAllActiveRules();
		// iterate and apply each rule with respective rule processor
		// applying chain of responsibility pattern
		for (PricingRuleDTO pricingRuleDTO : pricingRuleDTOs) {
			// using factory pattern, getting the appropriate processor
			RuleProcessor ruleProcessor = RuleProcessorFactory
					.getRuleProcessor(RuleType.fromString(pricingRuleDTO.ruleType()));
			// Based on the rule, it apply the pricing calculation
			PricingRuleProcessor pricingRuleProcessor = new PricingRuleProcessor(ruleProcessor);
			pricingRuleProcessor.calculatePrice(productPrice, pricingRuleDTO.conditionJson(), pricingRuleDTO.value());
		}
		return new ProductPriceDTO(productPrice.getBasePrice(), productPrice.getAppliedRuleTypes(),
				productPrice.getFinalComputedPrice());
	}
}
