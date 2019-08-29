package com.yangxp.ad.adgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class AdGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdGatewayApplication.class, args);
    }

}
