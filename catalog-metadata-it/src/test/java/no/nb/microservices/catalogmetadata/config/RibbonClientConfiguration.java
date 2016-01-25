package no.nb.microservices.catalogmetadata.config;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonClientConfiguration {

    @Bean
    public ILoadBalancer ribbonLoadBalancer() {
        return new BaseLoadBalancer();
    }
}
