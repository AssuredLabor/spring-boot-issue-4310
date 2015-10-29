package org.example;

import org.example.configuration.ApplicationConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Demo app to show off Spring-Boot Issue 4310
 */
@Configuration
@Import({ApplicationConfiguration.class})
@EnableTransactionManagement
@Order(Ordered.HIGHEST_PRECEDENCE)
@SpringBootApplication
public class SpringBoot4310App extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBoot4310App.class);
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringBoot4310App.class).run(args);
	}

}