package com.hb0730.boot.admin.config.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/12
 */
@Configuration
public class SpringdocConfiguration {

//    @Bean
//    public GroupedOpenApi createRestApi() {
//        return GroupedOpenApi.builder()
//            .pathsToMatch("/")
//            .build();
//    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(
                new Info()
                    .title("boot-admin系统API")
                    .contact(
                        new Contact()
                            .name("hb0730")
                            .url("https://blog.hb00730.me")
                            .email("huangbing0730@gmail.com")
                    )
                    .version("v1.0.0")
            );
    }
}
