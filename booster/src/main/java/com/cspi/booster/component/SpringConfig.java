package com.cspi.booster.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	@Bean
	public ComponentService componentService() {
		return new ComponentServiceImpl();
	}
	
	@Bean
	public ComponentRepository componentRespository() {
		return new LocalComponentRespository();
	}
}
