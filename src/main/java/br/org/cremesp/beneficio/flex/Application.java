package br.org.cremesp.beneficio.flex;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2) //
				.select() //
				.apis(RequestHandlerSelectors.any()) //
				.paths(PathSelectors.any()) //
				.build() //
				.apiInfo(apiInfo()) //
				.tags( //
						new Tag("API Reembolso", "Métodos para sala") //
						, new Tag("API Beneficio", "Métodos para horário") //
						, new Tag("API Report", "Métodos para report"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder() //
				.title("API - Benefício Flex") //
				.description("APIs de Benefício Flex do Cremesp") //
				.version("1.0.0") //
				.license("") //
				.licenseUrl("") //
				.contact(new Contact("Cremesp", "https://www.cremesp.org.br", "contato@cremesp.org.br")) //
				.build();
	}

}
