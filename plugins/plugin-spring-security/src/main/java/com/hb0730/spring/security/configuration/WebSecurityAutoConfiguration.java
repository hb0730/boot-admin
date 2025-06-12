package com.hb0730.spring.security.configuration;

import com.hb0730.base.meta.ICurrentUserService;
import com.hb0730.spring.security.configuration.config.SecurityConfig;
import com.hb0730.spring.security.core.context.TransmittableThreadLocalSecurityContextHolderStrategy;
import com.hb0730.spring.security.core.filter.TokenAuthenticationFilter;
import com.hb0730.spring.security.core.handler.AuthenticationEntryPointHandler;
import com.hb0730.spring.security.core.handler.ForbiddenAccessDeniedHandler;
import com.hb0730.spring.security.core.service.SecurityConfigService;
import com.hb0730.spring.security.core.service.SecurityHolder;
import com.hb0730.spring.security.core.service.SecurityHolderDelegate;
import com.hb0730.spring.security.core.service.UserService;
import com.hb0730.spring.security.core.strategy.AuthorizeRequestsCustomizer;
import com.hb0730.spring.security.core.strategy.ConfigAuthorizeRequestsCustomizer;
import com.hb0730.spring.security.core.strategy.ConsoleAuthorizeRequestsCustomizer;
import com.hb0730.spring.security.core.strategy.PermitAllAnnotationAuthorizeRequestsCustomizer;
import com.hb0730.spring.security.core.strategy.StaticResourceAuthorizeRequestsCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

/**
 * web security 自动配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
@AutoConfiguration
@EnableConfigurationProperties(SecurityConfig.class)
// web支持
@EnableWebSecurity
// 对注解@PreAuthorize, @PostAuthorize, @PreFilter, @PostFilter生效
@EnableMethodSecurity
public class WebSecurityAutoConfiguration {

    /**
     * @return 认证失败处理器
     */
    @Bean
    @ConditionalOnMissingBean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointHandler();
    }

    /**
     * @return 权限不足处理器
     */
    @Bean
    @ConditionalOnBean(SecurityHolder.class)
    public AccessDeniedHandler accessDeniedHandler(SecurityHolder securityHolder) {
        return new ForbiddenAccessDeniedHandler(securityHolder);
    }

    /**
     * @return 密码加密器
     */
    @Bean
    @ConditionalOnBean({SecurityConfigService.class})
    public PasswordEncoder passwordEncoder(SecurityConfigService securityConfigService) {
        return securityConfigService.passwordEncoder();
    }

    /**
     * AuthenticationManager 不是bean
     * 重写父类方法可注入 AuthenticationManager
     *
     * @param authenticationConfiguration configuration
     * @return AuthenticationManagerBean
     * @throws Exception Exception
     */
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    /**
     * 权限默认前缀
     *
     * @return .
     */
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    /**
     * 声明调用 {@link SecurityContextHolder#setStrategyName(String)} 方法
     * 设置使用 {@link TransmittableThreadLocalSecurityContextHolderStrategy} 作为 Security 的上下文策略
     *
     * @return 替换策略
     */
    @Bean
    public MethodInvokingFactoryBean securityContextHolderMethodInvokingFactoryBean() {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
        methodInvokingFactoryBean.setTargetMethod("setStrategyName");
        methodInvokingFactoryBean.setArguments(TransmittableThreadLocalSecurityContextHolderStrategy.class.getName());
        return methodInvokingFactoryBean;
    }

    /**
     * 认证过滤器
     *
     * @param service 用户服务
     * @return token 认证过滤器
     */
    @Bean
    @ConditionalOnBean({UserService.class, SecurityConfigService.class})
    public TokenAuthenticationFilter authenticationTokenFilter(UserService service, SecurityConfigService securityConfigService) {
        return new TokenAuthenticationFilter(service, securityConfigService);
    }

    /**
     * - mybatis fill
     * - operator log
     * - log printer
     *
     * @return security holder 代理用于内部 framework 调用
     */
    @Bean
    @ConditionalOnBean(ICurrentUserService.class)
    public SecurityHolder securityHolder(ICurrentUserService currentUserService) {
        return new SecurityHolderDelegate(currentUserService);
    }

    /**
     * @param applicationContext applicationContext
     * @return 匿名接口安全策略
     */
    @Bean
    public PermitAllAnnotationAuthorizeRequestsCustomizer permitAllAnnotationAuthorizeRequestsCustomizer(ApplicationContext applicationContext) {
        return new PermitAllAnnotationAuthorizeRequestsCustomizer(applicationContext);
    }

    /**
     * @return 静态资源安全策略
     */
    @Bean
    public StaticResourceAuthorizeRequestsCustomizer staticResourceAuthorizeRequestsCustomizer() {
        return new StaticResourceAuthorizeRequestsCustomizer();
    }

    /**
     * @return 配置文件安全策略
     */
    @Bean
    public ConfigAuthorizeRequestsCustomizer configAuthorizeRequestsCustomizer(SecurityConfig securityConfig) {
        return new ConfigAuthorizeRequestsCustomizer(securityConfig);
    }

    /**
     * @param adminSeverContextPath adminSeverContextPath
     * @param managementEndpoints   managementEndpoints
     * @return 控制台安全策略
     */
    @Bean
    public ConsoleAuthorizeRequestsCustomizer consoleAuthorizeRequestsCustomizer(
            @Value("${spring.boot.admin.context-path:''}") String adminSeverContextPath,
            @Value("${management.endpoints.web.base-path:''}") String managementEndpoints) {
        return new ConsoleAuthorizeRequestsCustomizer(adminSeverContextPath, managementEndpoints);
    }

    /**
     * 安全过滤器链
     * <p>
     * <pre>
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl 表达式结果为 true 时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数, 参数表示权限, 则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数, 参数表示角色, 则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数, 参数表示权限, 则其权限可以访问
     * hasIpAddress        |   如果有参数, 参数表示IP地址, 如果用户IP和参数匹配, 则可以访问
     * hasRole             |   如果有参数, 参数表示角色, 则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过 remember-me 登录的用户访问
     * authenticated       |   用户登录后可访问
     * </pre>
     *
     * @param corsConfigurationSource 跨域配置
     * @return 安全过滤器链
     */
    @Bean
    public SecurityFilterChain filterChain(
            List<AuthorizeRequestsCustomizer> authorizeRequestsCustomizers,
            CorsConfigurationSource corsConfigurationSource,
            AuthenticationEntryPoint authenticationEntryPoint,
            AccessDeniedHandler accessDeniedHandler,
            TokenAuthenticationFilter authenticationTokenFilter,
            HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // 跨域配置
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                // 因为不使用session 禁用CSRF
                .csrf(AbstractHttpConfigurer::disable)
                // 基于 token 机制所以不需要 session
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 不设置响应报头
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                // 认证失败处理器
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint))
                // 权限不足处理器
                .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(accessDeniedHandler))
                // 设置请求权限策略
                .authorizeHttpRequests(registry -> authorizeRequestsCustomizers.forEach(customizer -> customizer.customize(registry)))
                // 其他请求需要认证
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                // 在密码认证器之前添加 token 过滤器
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
