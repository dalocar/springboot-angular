package com.carrascolimited.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

@SpringBootApplication
@ComponentScan(basePackages = "com.carrascolimited.springboot, com.belikesoftware.security.config")
@EnableJpaRepositories(basePackages = { "com.carrascolimited.springboot.repo" })
@EntityScan(basePackages = { "com.carrascolimited.springboot.domain", "com.belikesoftware.security.model" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public FilterRegistrationBean securityFilterChainRegistration() {
		DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
		delegatingFilterProxy.setTargetBeanName(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME);
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(delegatingFilterProxy);
		registrationBean.setName(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}

}
