package org.example.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Servlet;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;

import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.controller.StripesFilter;

@Configuration
@ComponentScan({"org.example.ebeans"})
public class ApplicationConfiguration {
	
	@Bean(name="stripesFilter")
	public FilterRegistrationBean stripesFilter() throws IOException {
		StripesFilter filter = new StripesFilter();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("ActionResolver.Packages", "org.example.action");
		
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
	
	@Bean(name="stripesServlet")
	public ServletRegistrationBean stripesServlet() {
		Servlet servlet = new DispatcherServlet();
	
		ServletRegistrationBean registration = new ServletRegistrationBean(servlet);
		List<String> urlMappings = new ArrayList<String>();
		urlMappings.add("/page/*");
		registration.setUrlMappings(urlMappings);
		
		return registration;
	}
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return (container -> {
			ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/page/error?invalidURL=true");
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/page/error?invalidURL=true");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/page/error");

			container.addErrorPages(error401Page, error404Page, error500Page);
		});
	}
}
