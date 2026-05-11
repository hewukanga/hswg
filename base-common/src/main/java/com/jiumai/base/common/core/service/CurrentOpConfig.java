package com.jiumai.base.common.core.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.current")
@Component(value="currentOpConfig")
public class CurrentOpConfig {

	private String currentOperatorServiceName;

	public String getCurrentOperatorServiceName() {
		return currentOperatorServiceName;
	}

	public void setCurrentOperatorServiceName(String currentOperatorServiceName) {
		this.currentOperatorServiceName = currentOperatorServiceName;
	}
	
}
