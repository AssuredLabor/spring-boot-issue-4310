package org.example.configuration.web;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureAfter(DispatcherServletAutoConfiguration.class)
@Slf4j
public class AttachmentsConfiguration extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/attachments/**").addResourceLocations("file:/tmp/attachments/");

		super.addResourceHandlers(registry);
	}

}