package com.carrascolimited.springboot;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.dozer.CustomConverter;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.carrascolimited.springboot.dozer.converters.LocalDateDozerConverter;
import com.carrascolimited.springboot.dozer.converters.LocalDateTimeDozerConverter;

@Configuration
@ComponentScan(basePackages = { "com.belike.fsegura.kumo.core.dozer" })
public class SpringDozerConfig {
	@Autowired
	private LocalDateDozerConverter localDateDozerConverter;

	@Autowired
	private LocalDateTimeDozerConverter localDateTimeDozerConverter;

	@Bean
	public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean() throws IOException {
		DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
		Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:dozer*Mapping.xml");
		dozerBeanMapperFactoryBean.setMappingFiles(resources);
		Map<String, CustomConverter> customConvertersWithId = new HashMap<>();
		customConvertersWithId.put("localDateTimeDozerConverter", localDateTimeDozerConverter);
		customConvertersWithId.put("localDateDozerConverter", localDateDozerConverter);
		dozerBeanMapperFactoryBean.setCustomConvertersWithId(customConvertersWithId);
		return dozerBeanMapperFactoryBean;
	}
}
