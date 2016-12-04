package com.arthaszeng.easyapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket CreateSwaggerConfig() {
        return new Docket( DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .ignoredParameterTypes()
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.arthaszeng.categoryapi"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Product CategoryEntity API",
                "Spring boot + swagger + mybatis",
                "1.0",
                "",
                new Contact("Arthas Zeng", "", ""),
                "",
                ""
        );

        return apiInfo;
    }

}