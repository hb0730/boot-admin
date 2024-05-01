package com.hb0730.springdoc.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${spring.application.name:boot-admin}") String appName,
            @Value("${spring.application.version:1.0.0}") String appVersion,
            @Value("${boot.admin.swagger.auth-header:Authorization}") String headerName) {
        return new OpenAPI()
                .info(
                        apiInfo(appName, appVersion)
                )
                .addSecurityItem(
                        new io.swagger.v3.oas.models.security.SecurityRequirement()
                                .addList("authorization")
                                .addList("tenant")
                )
                .paths(
                        new io.swagger.v3.oas.models.Paths()
                                .addPathItem(
                                        "/auth/logout",
                                        new io.swagger.v3.oas.models.PathItem()
                                                .post(
                                                        new io.swagger.v3.oas.models.Operation()
                                                                .operationId("logout")
                                                                .summary("退出登录")
                                                                .addTagsItem("系统：授权接口")
                                                                .responses(
                                                                        new io.swagger.v3.oas.models.responses.ApiResponses()
                                                                                .addApiResponse(
                                                                                        "200",
                                                                                        new io.swagger.v3.oas.models.responses.ApiResponse()
                                                                                                .description("退出登录成功")
                                                                                                .content(
                                                                                                        new io.swagger.v3.oas.models.media.Content()
                                                                                                                .addMediaType(
                                                                                                                        "application/json;charset=UTF-8",
                                                                                                                        new io.swagger.v3.oas.models.media.MediaType()
                                                                                                                                .schema(
                                                                                                                                        new io.swagger.v3.oas.models.media.Schema().$ref("#/components/schemas/com.hb0730.base.RJava.lang.String")
                                                                                                                                )
                                                                                                                )
                                                                                                )
                                                                                )
                                                                                .addApiResponse(
                                                                                        "500",
                                                                                        new io.swagger.v3.oas.models.responses.ApiResponse()
                                                                                                .description("退出登录异常")
                                                                                                .content(
                                                                                                        new io.swagger.v3.oas.models.media.Content()
                                                                                                                .addMediaType(
                                                                                                                        "application/json;charset=UTF-8",
                                                                                                                        new io.swagger.v3.oas.models.media.MediaType()
                                                                                                                                .schema(
                                                                                                                                        new io.swagger.v3.oas.models.media.Schema().$ref("#/components/schemas/com.hb0730.base.RJava.lang.String")
                                                                                                                                )
                                                                                                                )
                                                                                                )
                                                                                )
                                                                                .addApiResponse(
                                                                                        "401",
                                                                                        new io.swagger.v3.oas.models.responses.ApiResponse()
                                                                                                .description("认证失败")
                                                                                                .content(
                                                                                                        new io.swagger.v3.oas.models.media.Content()
                                                                                                                .addMediaType(
                                                                                                                        "application/json;charset=UTF-8",
                                                                                                                        new io.swagger.v3.oas.models.media.MediaType()
                                                                                                                                .schema(
                                                                                                                                        new io.swagger.v3.oas.models.media.Schema().$ref("#/components/schemas/com.hb0730.base.RJava.lang.String")
                                                                                                                                )
                                                                                                                )
                                                                                                )
                                                                                )
                                                                )
                                                )
                                )
                )
                .schemaRequirement("authorization",
                        securityScheme(headerName)
                ).schemaRequirement(
                        "tenant",
                        securityScheme("X-Tenant")
                );
    }


    /**
     * 管理端API
     *
     * @return .
     */
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("管理端接口")
                .pathsToMatch(
                        "/auth/**",
                        "/sys/**",
                        "/tenant/**"
                )
                .build();
    }

    /**
     * 履约端接口
     */
    @Bean
    public GroupedOpenApi performerApi() {
        return GroupedOpenApi.builder()
                .group("履约端接口")
                .pathsToExclude(
                        "/sys/**",
                        "/tenant/**"
                )
                .build();
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

    private SecurityScheme securityScheme(String headerName) {
        return new io.swagger.v3.oas.models.security.SecurityScheme()
                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.APIKEY)
                .in(io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER)
                .name(headerName)
                .description("访问令牌");
    }
}
