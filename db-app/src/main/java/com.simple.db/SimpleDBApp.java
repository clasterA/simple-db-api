package com.simple.db;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableAsync
@EnableCaching
@SpringBootApplication(
        scanBasePackages = {"com.simple.db"}
)
public class SimpleDBApp implements WebMvcConfigurer {

    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "local");
        new SpringApplicationBuilder(SimpleDBApp.class).run(args);
    }
}