package com.hzgy.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class Swagger2Config extends WebMvcConfigurationSupport {

	@Value("${swagger.api.groupName}")
	private String groupName;

	@Value("${swagger.api.basePackage}")
	private String basePackage;

	@Value("${swagger.api.key}")
	private String appKey;

	@Value("${swagger.api.name}")
	private String appName;

	@Value("${swagger.api.desc}")
	private String appDesc;

	@Value("${swagger.api.version}")
	private String appVersion;

	@Value("${swagger.api.termsOfServiceUrl}")
	private String termsOfServiceUrl;

	@Value("${swagger.api.contact.name}")
	private String contactName;

	@Value("${swagger.api.contact.url}")
	private String contactUrl;

	@Value("${swagger.api.contact.email}")
	private String contactEmail;

	@Value("${swagger.api.license}")
	private String license;

	@Value("${swagger.api.licenseUrl}")
	private String licenseUrl;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName(groupName)
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false).forCodeGeneration(true)
				.pathMapping("/").select()
				.apis(RequestHandlerSelectors.basePackage(basePackage))
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(appName)
				.description(appDesc)
				.termsOfServiceUrl(termsOfServiceUrl)
				.version(appVersion)
				.contact(new Contact(contactName, contactUrl, contactEmail))
				.build();
	}
}
