package com.smartretailpriceengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class SmartretailpriceengineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartretailpriceengineApplication.class, args);
	}

	@GetMapping("/checkhealth")
	@ResponseBody
	public String check() {
		return "Smart Retail Engine service running...";
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/product")
	public String productConsole() {
		return "productconsole";
	}

	@GetMapping("/rule")
	public String ruleConsole() {
		return "ruleconsole";
	}

	@GetMapping("/pricetesting")
	public String priceTestingScreen() {
		return "pricetestingscreen";
	}
}
