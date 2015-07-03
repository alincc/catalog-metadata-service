package no.nb.microservices.catalogmetadata;

import no.nb.htrace.annotations.EnableTracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableTracing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
