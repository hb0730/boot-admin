package com.hb0730.boot.admin.security.configuration;

import com.hb0730.boot.admin.security.filter.JwtTokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.filter.CorsFilter;

/**
 * Spring Security 配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
@Configurable
@RequiredArgsConstructor
// MVC 集成
@EnableWebSecurity
// 对 @PreAuthorize、@PostAuthorize、@PreFilter和@PostFilter支持
@EnableMethodSecurity
public class WebSecurityConfiguration {
    /**
     * 验证失败
     */
    private final AuthenticationEntryPoint authenticationEntryPointService;
    /**
     * 访问权限不足
     */
    private final AccessDeniedHandler accessDeniedServiceHandler;

    /*登出成功*/
    private final LogoutSuccessHandler logoutSuccessServiceHandler;
    /*跨域处理*/
    private final CorsFilter corsFilter;
    /*jwt token认证*/
    private final JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;
    /*用户详情服务*/
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security
            .csrf().disable()
            //异常的处理
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPointService)
            .accessDeniedHandler(accessDeniedServiceHandler)
            .and()
            //登录的处理
            .formLogin()
            .and()
            .userDetailsService(userDetailsService)
            // session禁用 采用token方式
            .sessionManagement().disable()
            // 授权
            .authorizeRequests()
            //对于登录login  开放
            .antMatchers("/auth/**", "/favicon.ico").permitAll()
            // options 开放
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // 其余认证访问
            .antMatchers("/**").authenticated()
            .and()
            // 登出
            .logout()
            .logoutUrl("/auth/logout")
            .logoutSuccessHandler(logoutSuccessServiceHandler)
            .and()
            //跨域
            .addFilterBefore(corsFilter, CorsFilter.class)
            // jwt token认证
            .addFilterBefore(jwtTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return security.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(ObjectPostProcessor<Object> objectPostProcessor) throws Exception {
        return new AuthenticationManagerBuilder(objectPostProcessor)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
            .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    /**
//     * 自定义前缀
//     * @return
//     */
//    @Bean
//    static GrantedAuthorityDefaults grantedAuthorityDefaults() {
//        return new GrantedAuthorityDefaults("MYPREFIX_");
//    }
}
