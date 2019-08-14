package br.com.zup.xyinc.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
@EnableSwagger2
@Configuration
public class SwaggerConfig {
 
	@Bean
	public Docket detalheApi() {
 
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
 
		docket
		.select()
		.apis(RequestHandlerSelectors.basePackage("br.com.zup.xyinc"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(this.informacoesApi().build());
 
		return docket;
	}
 
	private ApiInfoBuilder informacoesApi() {
 
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
		apiInfoBuilder.title("Api-dynamic");
		apiInfoBuilder.description("Api Dinamica para cadastrar entidades e persistir as mesmas com REST para vaga de emprego de Desenvolvedor III Java na Zup.");
		apiInfoBuilder.version("0.0.1");
		apiInfoBuilder.termsOfServiceUrl("Termo de uso: Deve ser usada para estudos.");
		apiInfoBuilder.contact(this.contato());
 
		return apiInfoBuilder;
 
	}
	private Contact contato() {
 
		return new Contact(
				"Daniel Rodrigo Pereira",
				"https://github.com/danielrodrigopereira/xyinc", 
				"danielrodrigopereira5@gmail.com");
	}
}