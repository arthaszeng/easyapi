package com.arthaszeng.easyapi.integration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan(basePackages = {"com.arthaszeng.easyapi"})
@PropertySources(value = {
        @PropertySource("classpath:application.properties")
})
@EnableAutoConfiguration
public class IntegrationTestConfiguration {
}