package com.smartretailpriceengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smartretailpriceengine.entity.PricingRule;

@Repository
public interface PricingRulesRepository extends JpaRepository<PricingRule, Long> {

	@Query("SELECT pr FROM PricingRule pr WHERE pr.isActive = 'Y' order by priority asc")
	List<PricingRule> findAllActiveRules();
}
