package com.github.cjqcn.springcloud.barrel.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author chenjinquan
 * @date 2018/3/25
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@Slf4j
public class GatewayApplication implements CommandLineRunner {

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${server.context-path:}")
    private String serverContextPath;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GatewayApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... strings) {
        log.info("Application is success, Index >> http://127.0.0.1:{}{}", serverPort, serverContextPath);
        log.info("Application is success, Swagger Url >> http://127.0.0.1:{}{}/swagger-ui.html", serverPort,
                serverContextPath);
    }
}
