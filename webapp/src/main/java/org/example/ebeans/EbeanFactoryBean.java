package org.example.ebeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;

@Component
public class EbeanFactoryBean implements FactoryBean<EbeanServer>, EnvironmentAware {

	@Resource(name="dataSource")
	DataSource dataSource;
	
	@Autowired
	CurrentUserProviderImpl currentUser;

	public EbeanFactoryBean() {
	}

	/**
	 * Properties used to configure EbeanServer instance.
	 */
	Properties properties = new Properties();

	@Override
	public EbeanServer getObject() throws Exception {

		ServerConfig config = new ServerConfig();
		config.setName("db");
		config.setCurrentUserProvider(currentUser);
		config.loadFromProperties(properties);
		config.setDataSource(dataSource);
		config.setDdlGenerate(false);
		config.setDdlRun(false);
		config.setDefaultServer(true);
		config.setRegister(true);
		
		List<String> packages = new ArrayList<>();
		packages.add("org.example.beans");

		config.setPackages(packages);
		//config.loadTestProperties();
		
		List<String> jars = new ArrayList<>();
		jars.add("spring-boot-stripes-ebeans-core-0.0.1-SNAPSHOT.jar");
		
		config.setJars(jars);

		return EbeanServerFactory.create(config);
	}

	@Override
	public Class<?> getObjectType() {
		return EbeanServer.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void setEnvironment(Environment environment) {

		loadProperties((AbstractEnvironment) environment);
	}

	/**
	 * Load into Properties (from Spring PropertySource implementations).
	 */
	private void loadProperties(AbstractEnvironment environment) {
		MutablePropertySources propertySources = environment.getPropertySources();
		for (PropertySource propertySource : propertySources) {
			if (propertySource instanceof PropertiesPropertySource) {// MapPropertySource
				properties.putAll(((PropertiesPropertySource) propertySource).getSource());
			}
		}
	}

}
