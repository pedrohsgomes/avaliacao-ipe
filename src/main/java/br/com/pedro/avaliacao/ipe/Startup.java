package br.com.pedro.avaliacao.ipe; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
@EnableSwagger2
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}
	
	@Bean
	public Docket swagger() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(this.getClass().getPackageName()))
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(true);
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Avaliação Java IPE")
				.description("API desenvolvido para desafio IPE")
				.version("1.0")
				.contact(new Contact(
						"Pedro Gomes",
						"https://github.com/pedrohsgomes",
						"pedrohsgomes@gmail.com"))
				.build();
	}

}
