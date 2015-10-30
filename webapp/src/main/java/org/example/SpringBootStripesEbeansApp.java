package org.example;

import org.example.configuration.ApplicationConfiguration;
import org.example.stripes.SpringBootVfs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import net.sourceforge.stripes.vfs.VFS;

/**
 * Demo app to show off Spring interaction with Stripes and Ebeans
 */
@Import({ApplicationConfiguration.class})
@SpringBootApplication
public class SpringBootStripesEbeansApp extends SpringBootServletInitializer {
	
	static {
		VFS.addImplClass(SpringBootVfs.class);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootStripesEbeansApp.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootStripesEbeansApp.class, args);
	}

}