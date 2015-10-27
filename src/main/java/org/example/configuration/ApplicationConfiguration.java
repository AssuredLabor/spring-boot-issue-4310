package org.example.configuration;

import org.example.configuration.web.AttachmentsConfiguration;
import org.example.configuration.web.FilterConfiguration;
import org.example.configuration.web.ServletConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@ImportResource({
	"classpath*:/database.xml"
})
@Import({
	FilterConfiguration.class,
	ServletConfiguration.class,
	AttachmentsConfiguration.class})
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Configuration
public class ApplicationConfiguration {
}
