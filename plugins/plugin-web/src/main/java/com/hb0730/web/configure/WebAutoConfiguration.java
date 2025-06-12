package com.hb0730.web.configure;

import com.hb0730.base.utils.StrUtil;
import com.hb0730.web.handler.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
@AutoConfiguration
public class WebAutoConfiguration implements WebMvcConfigurer {

    @Value("${zoom.api.prefix: }")
    private String apiPath;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        if (StrUtil.isBlank(apiPath)) {
            return;
        }
        // all controller
        configurer.addPathPrefix(apiPath,
                clazz -> clazz.isAnnotationPresent(RequestMapping.class));

//        AntPathMatcher matcher = new AntPathMatcher(".");
//        configurer.addPathPrefix(
//                apiPath,
//                clazz -> clazz.isAnnotationPresent(RestController.class)
//                        && matcher.match("com.hb0730.**.controller.**", clazz.getPackage().getName()));
    }

    /**
     * @return 全局异常处理器
     */
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

}
