package com.hb0730.boot.admin.security.config;

import com.hb0730.boot.admin.base.util.PasswordUtil;
import com.hb0730.boot.admin.security.filter.JwtTokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * Spring Security 配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/11
 */
@Configuration
// web 支持
@EnableWebSecurity
// 对注解
//  @PreAuthorize, @PostAuthorize, @PreFilte
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {
    private final UserDetailsService userDetailsService;
    private final CorsFilter corsFilter;
    private final JwtTokenAuthenticationFilter tokenAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // csrf 禁用
            .csrf().disable()
            // 异常处理
            .exceptionHandling()
//            // 认证异常
//            .authenticationEntryPoint()
//            // 授权异常
//            .accessDeniedHandler()
            .and()
            .userDetailsService(userDetailsService)
            // 防止iframe 造成跨域
            .headers()
            .frameOptions()
            .disable()
            .and()
            // session 禁用,采用token方式
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            // 授权
            .authorizeHttpRequests()
            // swagger 文档
            .requestMatchers("/swagger-ui.html").permitAll()
            .requestMatchers("/swagger-resources/**").permitAll()
            .requestMatchers("/webjars/**").permitAll()
            .requestMatchers("/*/api-docs/**").permitAll()
            // 静态资源等等
            .requestMatchers(
                HttpMethod.GET,
                "/*.html",
                "/*/*.html",
                "/*/*.css",
                "/*/*.js",
                "/webSocket/**"
            ).permitAll()
            //对于登录login  开放
            .requestMatchers("/auth/**", "/favicon.ico").permitAll()
            // options 开放
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // 文件
            .requestMatchers("/avatar/**").permitAll()
            .requestMatchers("/file/**").permitAll()
            // 其余认证访问
            .anyRequest().authenticated()
            .and()
            // filter
            .addFilterBefore(corsFilter, CorsFilter.class)
            .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
//    @Bean
//    public AuthenticationManager authenticationManager(ObjectPostProcessor<Object> builder) throws Exception {
//        return new AuthenticationManagerBuilder(builder)
//            .authenticationProvider(new CaptchaAuthenticationProvider(captchaUserDetailsService, captchaService))
//            .authenticationProvider(new OpenIdAuthenticationProvider(openIdDetailsService))
//            .build();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
//        return builder
//            .userDetailsService(userDetailsService)
//            .passwordEncoder(passwordEncoder())
//            .and()
//            .build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordUtil.defaultPasswordEncoder();
    }

    /**
     * 移除{@code  ROLE_}前缀,
     * 方便使用{@link  com.hb0730.boot.admin.security.expression.PermissionService#hashPermission(String)}或者Spring
     * Security自己的方法
     *
     * @return .
     * @see org.springframework.security.config.annotation.method.configuration.PrePostMethodSecurityConfiguration
     */
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }
}
