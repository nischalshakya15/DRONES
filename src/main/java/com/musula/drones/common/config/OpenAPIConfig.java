package com.musula.drones.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
  /**
   * It creates a new OpenAPI object and returns it.
   *
   * @return OpenAPI object
   */
  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("DRONES")
                .description("CONTAINS THE API FOR DRONES")
                .contact(new Contact().email("nischalshakya95@gmail.com"))
                .license(new License().name("Musula Soft"))
                .version("1.0"));
  }
}
