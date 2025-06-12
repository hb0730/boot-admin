package com.hb0730.spring.security.core.strategy;

import com.hb0730.base.utils.CollectionUtil;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * API @PermitAll 认证策略
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
public class PermitAllAnnotationAuthorizeRequestsCustomizer extends AuthorizeRequestsCustomizer {
    private final ApplicationContext applicationContext;

    public PermitAllAnnotationAuthorizeRequestsCustomizer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        // 获取匿名接口
        Map<HttpMethod, Set<String>> permitAllUrls = getPermitAllUrlsFromAnnotations();
        // PermitAll 无需认证
        registry
                .requestMatchers(permitAllUrls.get(null).toArray(new String[0])).permitAll()
                .requestMatchers(HttpMethod.GET, permitAllUrls.get(HttpMethod.GET).toArray(new String[0])).permitAll()
                .requestMatchers(HttpMethod.POST, permitAllUrls.get(HttpMethod.POST).toArray(new String[0])).permitAll()
                .requestMatchers(HttpMethod.PUT, permitAllUrls.get(HttpMethod.PUT).toArray(new String[0])).permitAll()
                .requestMatchers(HttpMethod.DELETE, permitAllUrls.get(HttpMethod.DELETE).toArray(new String[0])).permitAll();
    }

    /**
     * 通过注解获取所有匿名接口
     *
     * @return 匿名接口
     */
    private Map<HttpMethod, Set<String>> getPermitAllUrlsFromAnnotations() {
        Set<String> getList = new HashSet<>();
        Set<String> postList = new HashSet<>();
        Set<String> putList = new HashSet<>();
        Set<String> deleteList = new HashSet<>();
        Set<String> requestList = new HashSet<>();
        // 获取 RequestMappingHandlerMapping
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        // 获得接口对应的 HandlerMethod
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = requestMappingHandlerMapping.getHandlerMethods();
        // 获得有 @PermitAll 注解的接口
        handlerMethodMap.forEach((mapping, method) -> {
            // 非 @PermitAll 则跳过
            if (!method.hasMethodAnnotation(PermitAll.class)) {
                return;
            }
            if (mapping.getPatternsCondition() == null &&
                    mapping.getPathPatternsCondition() == null) {
                return;
            }
            Set<String> urls = new HashSet<>();
            if (mapping.getPatternsCondition() != null) {
                urls = mapping.getPatternsCondition().getPatterns();
            } else if (mapping.getPathPatternsCondition() != null) {
                Set<PathPattern> patterns = mapping.getPathPatternsCondition().getPatterns();
                urls = patterns.stream().map(PathPattern::getPatternString).collect(Collectors.toSet());
            } else {
                urls = null;
            }
            if (CollectionUtil.isEmpty(urls)) {
                return;
            }
            Set<RequestMethod> methods = mapping.getMethodsCondition().getMethods();
            // 为空证明为 @RequestMapping
            if (methods.isEmpty()) {
                requestList.addAll(urls);
            }
            // 根据请求方法过滤
            Set<String> finalUrls = urls;
            methods.forEach(requestMethod -> {
                switch (requestMethod) {
                    case GET:
                        getList.addAll(finalUrls);
                        break;
                    case POST:
                        postList.addAll(finalUrls);
                        break;
                    case PUT:
                        putList.addAll(finalUrls);
                        break;
                    case DELETE:
                        deleteList.addAll(finalUrls);
                        break;
                    default:
                        break;
                }
            });
        });
        // 设置返回
        Map<HttpMethod, Set<String>> result = new HashMap<>();
        result.put(HttpMethod.GET, getList);
        result.put(HttpMethod.POST, postList);
        result.put(HttpMethod.PUT, putList);
        result.put(HttpMethod.DELETE, deleteList);
        result.put(null, requestList);
        return result;
    }

}
