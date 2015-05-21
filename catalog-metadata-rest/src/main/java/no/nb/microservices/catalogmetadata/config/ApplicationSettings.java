package no.nb.cloud.metadata.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "service-starter")
public class ApplicationSettings {

}
