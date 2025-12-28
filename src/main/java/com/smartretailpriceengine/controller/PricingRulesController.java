package com.smartretailpriceengine.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartretailpriceengine.dto.PricingRuleDTO;
import com.smartretailpriceengine.entity.PricingRule;
import com.smartretailpriceengine.exception.PricingRuleNotFoundException;
import com.smartretailpriceengine.service.PricingRulesService;

@RestController
@RequestMapping("/api/rules/")
public class PricingRulesController {

	private final PricingRulesService service;

	public PricingRulesController(PricingRulesService service) {
		this.service = service;
	}

	@GetMapping("/find/{ruleId}")
	public ResponseEntity<PricingRuleDTO> find(@PathVariable("ruleId") long ruleId)
			throws PricingRuleNotFoundException {
		return ResponseEntity.ok(service.find(ruleId));
	}

	@GetMapping("/findall")
	public ResponseEntity<List<PricingRuleDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@PostMapping("/save")
	public ResponseEntity<PricingRuleDTO> save(@RequestBody PricingRule pricingRule) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pricingRule));
	}

	@PutMapping("/update/{ruleId}")
	public ResponseEntity<PricingRuleDTO> update(@PathVariable("ruleId") long ruleId,
			@RequestBody PricingRule pricingRule) throws PricingRuleNotFoundException {
		return ResponseEntity.ok(service.update(ruleId, pricingRule));
	}

	@DeleteMapping("/delete/{ruleId}")
	public HeadersBuilder<?> delete(@PathVariable("ruleId") long ruleId) throws PricingRuleNotFoundException {
		service.delete(ruleId);
		return ResponseEntity.noContent();
	}
}
