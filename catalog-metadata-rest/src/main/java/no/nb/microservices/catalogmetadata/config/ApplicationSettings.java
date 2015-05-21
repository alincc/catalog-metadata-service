package no.nb.microservices.catalogmetadata.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "service-starter")
public class ApplicationSettings {

}
