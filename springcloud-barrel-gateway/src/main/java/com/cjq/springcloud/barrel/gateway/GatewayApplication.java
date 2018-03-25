package com.cjq.springcloud.barrel.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author jqChan
 * @date 2018/3/25
 */
@SpringBootApplication
@EnableDiscoveryClient(autoRegister = true)
@EnableZuulProxy
@Slf4j
public class GatewayApplication implements CommandLineRunner {

    @Value("${server.port:8080}")
    private String serverPort;


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GatewayApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... strings) {
        log.info("Application is success, Index >> http://127.0.0.1:{}", serverPort);
    }
}
