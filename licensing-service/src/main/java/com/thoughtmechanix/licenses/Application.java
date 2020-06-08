package com.thoughtmechanix.licenses;

import com.thoughtmechanix.licenses.events.models.OrganizationChangeModel;
import com.thoughtmechanix.licenses.utils.UserContextInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableResourceServer
@EnableBinding(Sink.class)
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    /*@LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (Objects.isNull(interceptors)) {
            restTemplate.setInterceptors(Collections.singletonList(
                    new UserContextInterceptor()
            ));
        } else {
            interceptors.add(new UserContextInterceptor());
            restTemplate.setInterceptors(interceptors);
        }
        return restTemplate;
    }*/

    @LoadBalanced
    @Bean
    public OAuth2RestTemplate oauth2RestTemplate(
            OAuth2ClientContext oauth2ClientContext,
            OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }

    /*@StreamListener(Sink.INPUT)
    public void loggerSink(OrganizationChangeModel orgChange) {
        logger.debug("Received an event for organization id {}", orgChange.getOrganizationId());
    }*/

    @Bean
    public Sampler defaultSampler() { return new AlwaysSampler();}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
