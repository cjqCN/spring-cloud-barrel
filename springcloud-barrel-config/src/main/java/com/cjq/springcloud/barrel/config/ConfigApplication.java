package com.cjq.springcloud.barrel.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;


@EnableConfigServer
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class ConfigApplication implements CommandLineRunner {

	@Value("${server.port:8585}")
	private String serverPort;


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ConfigApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... strings) {
		log.info("Application is success, Index >> http://127.0.0.1:{}", serverPort);
	}

}