package org.example.configuration.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.sourceforge.stripes.controller.DispatcherServlet;

@Configuration
public class ServletConfiguration extends WebMvcConfigurerAdapter {

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
