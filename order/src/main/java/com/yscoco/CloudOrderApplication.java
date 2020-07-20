package com.yscoco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Xiong
 * @date 2020-07-10 10:55
 */
@SpringBootApplication
public class CloudOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudOrderApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
