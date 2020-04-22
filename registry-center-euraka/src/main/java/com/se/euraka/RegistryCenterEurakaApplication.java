package com.se.euraka;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author Science
 * @Date 2020/3/28 23:10
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryCenterEurakaApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegistryCenterEurakaApplication.class, args);
    }
}
