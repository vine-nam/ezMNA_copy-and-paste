package com.cspi.booster;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cspi.booster.application.ApplicationService;
import com.cspi.booster.component.ComponentRepository;
import com.cspi.booster.component.ComponentService;
import com.cspi.booster.component.ComponentServiceImpl;
import com.cspi.booster.component.LocalComponentRespository;

@Configuration
public class SpringConfig {

	@Bean
	public ComponentService componentService() {
		return new ComponentServiceImpl(componentRespository());
	}
	
	@Bean
	public ComponentRepository componentRespository() {
		return new LocalComponentRespository();
	}

	@Bean
	public ApplicationService applicationService() {
		return new ApplicationService();
	}
	
}
