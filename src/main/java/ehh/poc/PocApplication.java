package ehh.poc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.client.ApiClient;
import io.swagger.client.api.PersonControllerApi;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ConfigurationProperties
@RefreshScope
@Configuration
@EnableSwagger2
public class PocApplication {

	@Value("${url.person}")
	private String urlPerson;
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("ehh.poc"))
				.paths(PathSelectors.any()).build();
	}
	
	@Bean
	public PersonControllerApi personControllerApi(){
		ApiClient apiClient = io.swagger.client.Configuration.getDefaultApiClient();
		apiClient.setBasePath(urlPerson);
		PersonControllerApi personControllerApi = new PersonControllerApi(apiClient);
		
		return personControllerApi;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PocApplication.class, args);
	}
}
