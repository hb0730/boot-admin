package com.hb0730.security.config;

import com.hb0730.base.utils.PasswordUtil;
import com.hb0730.security.filter.JwtTokenAuthenticationFilter;
import com.hb0730.security.handler.TokenAccessDeniedHandler;
import com.hb0730.security.handler.TokenAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Configuration
// web支持
@EnableWebSecurity
// 对注解@PreAuthorize, @PostAuthorize, @PreFilter, @PostFilter生效
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {
    private final UserDetailsService userDetailsService;
    private final CorsConfigurationSource corsConfigurationSource;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint;
    private final JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;


    /**
     * 安全过滤链
     *
     * @param http .
     * @return .
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // csrf 禁用
                .csrf(AbstractHttpConfigurer::disable)
                // 跨域配置
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                // 防止iframe 造成的跨域攻击
                .headers(
                        (headers) ->
                                headers.frameOptions(
                                        HeadersConfigurer.FrameOptionsConfig::disable
                                )
                )
                // 禁用session
                .sessionManagement(
                        sessionManagement ->
                                sessionManagement
                                        .sessionCreationPolicy(
                                                org.springframework.security.config.http.SessionCreationPolicy.STATELESS
                                        )
                )
                //禁用form表单登录
                .formLogin(AbstractHttpConfigurer::disable)
                //禁用httpBasic
                .httpBasic(AbstractHttpConfigurer::disable)
                //异常处理
                .exceptionHandling(
                        exceptionHandling ->
                                exceptionHandling
                                        .authenticationEntryPoint(tokenAuthenticationEntryPoint)
                                        .accessDeniedHandler(tokenAccessDeniedHandler)
                )
                // userDetailsService
                .userDetailsService(userDetailsService)
                // logout
                .logout(
                        logout -> logout.logoutUrl("/auth/logout")
                                .logoutSuccessHandler(logoutSuccessHandler)
                )
                // 认证管理
                .authorizeHttpRequests(
                        authorizeRequests ->
                                // swagger文件
                                authorizeRequests
                                        // swagger 文档
                                        .requestMatchers(
                                                "/swagger-ui.html",
                                                "/swagger-ui/**",
                                                "/swagger-resources/**",
                                                "/webjars/**",
                                                "/api-docs/**",
                                                "/*/api-docs/**"
                                        ).permitAll()
                                        // 静态资源
                                        .requestMatchers(
                                                HttpMethod.GET,
                                                "/*.html",
                                                "/*/*.html",
                                                "/*/*.css",
                                                "/*/*.js",
                                                "/css/**",
                                                "/js/**",
                                                "/images/**",
                                                "/webjars/**",
                                                "/*/favicon.ico"
                                        ).permitAll()
                                        // 对于login开放
                                        .requestMatchers("/auth/login").permitAll()
                                        //vue options请求开放
                                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                        //文件开放
                                        .requestMatchers("/file/**", "/avatar/**").permitAll()
                                        // 其他请求需要认证
                                        .anyRequest().authenticated()
                )
                // 添加jwt过滤器
                .addFilterBefore(jwtTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // 解析JWT，重新设置上下文
                .addFilterBefore(jwtTokenAuthenticationFilter, LogoutFilter.class)

        ;
        return http.build();
    }

    /**
     * 认证管理
     *
     * @param configuration .
     * @return .
     * @throws Exception .
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * 密码编码器
     *
     * @return .
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordUtil.defaultPasswordEncoder();
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
}