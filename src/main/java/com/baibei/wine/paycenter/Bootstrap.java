package com.baibei.wine.paycenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan("com.baibei.wine.paycenter")
@ImportResource({"classpath:config/spring-context.xml"})
public class Bootstrap extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);

    }

}
