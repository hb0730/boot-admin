package com.hb0730.base.conf.rpc;

import com.hb0730.base.utils.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.Properties;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/23
 */
@Slf4j
public class SofaRpcEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private final static String DEFAULT_PROFILE = "boot-admin-rpc.properties";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String profile = DEFAULT_PROFILE;
        // 根据不同的active配置加载不同的配置文件
        String active = environment.getProperty("spring.profiles.active");
        if (StrUtil.isNotBlank(active)) {
            profile = "boot-admin-rpc-" + active + ".properties";
        }
        // 获取对应的配置文件properties
        ResourceLoader resourceLoader = application.getResourceLoader();
        resourceLoader = (resourceLoader != null) ? resourceLoader : new DefaultResourceLoader();

        Properties activeProperties = getProfiles(profile, resourceLoader);
        Properties defaultProfile = getProfiles(DEFAULT_PROFILE, resourceLoader);

        // 优先级：active > default,无需合并
        if (null != activeProperties) {
            PropertiesPropertySource propertySource = new PropertiesPropertySource(profile, activeProperties);
            environment.getPropertySources().addLast(propertySource);
        }
        if (null != defaultProfile) {
            PropertiesPropertySource propertySource = new PropertiesPropertySource(DEFAULT_PROFILE, defaultProfile);
            environment.getPropertySources().addLast(propertySource);
        }

    }

    private Properties getProfiles(String profile, ResourceLoader resourceLoader) {
        Resource resource = resourceLoader.getResource("classpath:" + profile);
        if (resource.exists()) {
            try {
                Properties properties = new Properties();
                properties.load(resource.getInputStream());
                return properties;
            } catch (Exception e) {
                log.error("加载配置文件失败", e);
            }
        }
        return null;
    }
}
