package com.github.cjqcn.springcloud.barrel.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chenjinquan
 * @date 2018/3/25
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class ResourceApplication implements CommandLineRunner {


	@Value("${server.port:18768}")
	private String serverPort;

	@Value("${server.context-path:}")
	private String serverContextPath;

	@Value("${instance.id:instance_1}")
	String instanceId;

	//=================================================================================

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ResourceApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... strings) {
		System.setProperty("instance_id", instanceId);
		log.info("Application is success, Index >> http://127.0.0.1:{}{}", serverPort, serverContextPath);
		log.info("Application is success, Swagger Url >> http://127.0.0.1:{}{}/swagger-ui.html", serverPort, serverContextPath);
	}
}
