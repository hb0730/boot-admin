package com.hb0730.configuration.swagger;

import com.hb0730.security.config.SecurityProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/21
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${spring.application.name:boot-admin}") String appName,
            @Value("${spring.application.version:1.0.0}") String appVersion,
            SecurityProperties properties) {
        return new OpenAPI()
                .info(
                        apiInfo(appName, appVersion)
                )
                .addSecurityItem(
                        new io.swagger.v3.oas.models.security.SecurityRequirement()
                                .addList("authorization")
                )
                .schemaRequirement("authorization",
                        securityScheme(properties)

                );
    }


    private Info apiInfo(String appName,
                         String appVersion) {
        return new io.swagger.v3.oas.models.info.Info()
                .title("Boot Admin API")
                .version(appVersion)
                .description("API for " + appName)
                .contact(
                        new io.swagger.v3.oas.models.info.Contact()
                                .name("hb0730")
                                .url("https://blog.hb0730.me")
                );
    }

    private SecurityScheme securityScheme(SecurityProperties properties) {
        return new io.swagger.v3.oas.models.security.SecurityScheme()
                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.APIKEY)
                .in(io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER)
                .name(properties.getHeaderName())
                .description("访问令牌");
    }

}
