package com.smartretailpriceengine.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ ProductNotFoundException.class, PricingRuleNotFoundException.class })
	public ResponseEntity<String> handleProductNotFoundException(Throwable th) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(th.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_CONTENT)
	public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
}
