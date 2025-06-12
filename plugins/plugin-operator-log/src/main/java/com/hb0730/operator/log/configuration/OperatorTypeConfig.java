package com.hb0730.operator.log.configuration;

import com.hb0730.operator.log.core.factory.OperatorTypeDefinition;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.Objects;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/22
 */
@Configuration
public class OperatorTypeConfig implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 将OperatorTypeDefinition所有的实现类注册到spring容器中
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(OperatorTypeDefinition.class));

        for (BeanDefinition bd : scanner.findCandidateComponents("com.hb0730.zoom")) {
            GenericBeanDefinition gbd = (GenericBeanDefinition) bd;
            gbd.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
            registry.registerBeanDefinition(Objects.requireNonNull(gbd.getBeanClassName()), gbd);
        }

    }
}
