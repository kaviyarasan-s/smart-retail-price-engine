package com.smartretailpriceengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartretailpriceengine.entity.PricingRule;

@Repository
public interface PricingRulesRepository extends JpaRepository<PricingRule, Long> {

}
