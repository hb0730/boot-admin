package com.hb0730.springdoc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.hb0730.base.pool.ConstPool;
import com.hb0730.springdoc.config.SwaggerConfig;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/23
 */
@ConditionalOnClass({OpenAPI.class})
@EnableConfigurationProperties(SwaggerConfig.class)
@ConditionalOnProperty(name = "springdoc.api-docs.enabled", havingValue = "true")
@AutoConfiguration
public class SwaggerAutoConfiguration {
    @Value("${zoom.api.prefix: }")
    private String apiPrefix;

    /**
     * 构建openAPI
     *
     * @param swaggerConfig 配置
     * @return .
     */
    @Bean
    public OpenAPI openApi(SwaggerConfig swaggerConfig) {
        Map<String, SecurityScheme> securitySchemas = this.buildSecuritySchemes();
        OpenAPI api = new OpenAPI()
                // 接口信息
                .info(this.buildInfo(swaggerConfig))
                // 接口安全配置
                .components(new Components().securitySchemes(securitySchemas));
        securitySchemas.keySet()
                .forEach(key -> api.addSecurityItem(new SecurityRequirement().addList(key)));
        return api;
    }

    /**
     * 构建文档信息
     *
     * @param properties 配置
     * @return .
     */
    public Info buildInfo(SwaggerConfig properties) {
        return new Info()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .version(properties.getVersion())
                .contact(new Contact().name(properties.getAuthor()).url(properties.getUrl()).email(properties.getEmail()))
                .license(new License().name(properties.getLicense()).url(properties.getLicenseUrl()));
    }

    /**
     * 构建安全方案
     *
     * @return .
     */
    private Map<String, SecurityScheme> buildSecuritySchemes() {
        final String tokenHeaderName = "T-Token";
        Map<String, SecurityScheme> securitySchemes = new HashMap<>();
        SecurityScheme securityScheme = new SecurityScheme()
                // 类型
                .type(SecurityScheme.Type.APIKEY)
                // 请求头的 name
                .name(tokenHeaderName)
                // token 所在位置
                .in(SecurityScheme.In.HEADER)
                .description("访问令牌");
        securitySchemes.put(tokenHeaderName, securityScheme);
        return securitySchemes;
    }

    /**
     * @param properties  properties
     * @param beanFactory beanFactory
     * @return 所有模块的 api 分组
     */
    @Bean
    public GroupedOpenApi allGroupedOpenApi(ConfigurableListableBeanFactory beanFactory,
                                            SwaggerConfig properties) {
        // 全部
        GroupedOpenApi all = this.buildGroupedOpenApi(apiPrefix, "全部", List.of("*"));
        // 注册模块分组 api
        if (CollectionUtil.isNotEmpty(properties.getGroupedApi())) {
            properties.getGroupedApi().forEach((t, v) -> {
                GroupedOpenApi api = this.buildGroupedOpenApi(apiPrefix, v.getGroup(), v.getPath());
                beanFactory.registerSingleton(t + "GroupedOpenApi", api);
            });
        }
        return all;
    }

    /**
     * 构建 api 分组
     *
     * @param group group
     * @param path  path
     * @return group
     */
    private GroupedOpenApi buildGroupedOpenApi(String apiPrefix, String group, List<String> path) {
        List<String> pathPatterns = path.stream().map(p -> {
            String pathPattern = "/" + p + "/**";
            if (StrUtil.isNotBlank(apiPrefix)) {
                pathPattern = apiPrefix + pathPattern;
            }
            return pathPattern;
        }).toList();
        return GroupedOpenApi.builder()
                .group(group)
                .pathsToMatch(pathPatterns.toArray(new String[0]))
                .addOperationCustomizer((operation, handlerMethod) -> operation
                        .addParametersItem(buildSecurityHeaderParameter()))
                .build();
    }

    /**
     * @return Authorization 认证请求头参数
     */
    private Parameter buildSecurityHeaderParameter() {
        return new Parameter()
                .name(ConstPool.HEADER_X_ACCESS_TOKEN)
                .description("认证 Token")
                .in(String.valueOf(SecurityScheme.In.HEADER))
                .schema(new StringSchema()
                        .name(ConstPool.HEADER_X_ACCESS_TOKEN)
                        .description("认证 Token"));
    }
}
