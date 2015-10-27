package org.example.configuration.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.DispatcherType;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.stripes.controller.StripesFilter;

@Configuration 
@Slf4j
public class FilterConfiguration {
	
	@Bean(name="hiddenHttpMethodFilter")
	public FilterRegistrationBean hiddenHttpMethodFilter() throws IOException {
		HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
		
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/social/connect/*");
		registration.setUrlPatterns(urlPatterns);
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 100);
		return registration;
	}
	
	@Bean(name="stripesFilter")
	public FilterRegistrationBean stripesFilter() throws IOException {
		StripesFilter filter = new StripesFilter();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("ActionResolver.Packages", "org.example.action");
		params.put("Extension.Packages", "org.stripesstuff.plugin.security");
		params.put("ResourceBundles.BaseNames", "StripesResources");
		
		
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setInitParameters(params);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/page/*");
		registration.setUrlPatterns(urlPatterns);
		registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.INCLUDE, DispatcherType.FORWARD, DispatcherType.ERROR);

		registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 130);
		return registration;
	}

}
