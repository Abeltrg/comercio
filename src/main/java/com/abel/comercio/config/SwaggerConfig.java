package com.abel.comercio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author abeltrg
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.abel.comercio")).paths(PathSelectors.any()).build()
				.apiInfo(getApiInfo());
	}


	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("Comercio API").description("Documentación para Comercio API")
				.version("1.0.0").license("LICENCE").licenseUrl("LICENCE_URL")
				.contact(new Contact("abeltrg", "www.abeltrg.com", "abeltrg@gmail.com")).build();
	}

}
