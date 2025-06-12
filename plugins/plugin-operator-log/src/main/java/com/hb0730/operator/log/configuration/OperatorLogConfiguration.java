package com.hb0730.operator.log.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hb0730.base.meta.ICurrentUserService;
import com.hb0730.desensitize.core.modifier.DesensitizeSerializerModifier;
import com.hb0730.desensitize.core.serializer.MapDesensitizeSerializer;
import com.hb0730.operator.log.configuration.config.OperatorLogConfig;
import com.hb0730.operator.log.core.aspect.OperatorLogAspect;
import com.hb0730.operator.log.core.model.OperatorLogModelBuilder;
import com.hb0730.operator.log.core.service.OperatorLogFrameworkService;
import com.hb0730.operator.log.core.service.OperatorLogFrameworkServiceDelegate;
import com.hb0730.operator.log.core.util.OperatorLogs;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/10
 */
@AutoConfiguration
@EnableConfigurationProperties(OperatorLogConfig.class)
public class OperatorLogConfiguration {

    /**
     * 操作日志委托类
     *
     * @param service service
     * @return delegate
     */
    @Bean
    @Primary
    @ConditionalOnBean(OperatorLogFrameworkService.class)
    public OperatorLogFrameworkServiceDelegate operatorLogFrameworkService(OperatorLogFrameworkService service) {
        return new OperatorLogFrameworkServiceDelegate(service);
    }


    /**
     * 日志切面
     *
     * @param operatorLogConfig operatorLogConfig
     * @param service           service
     * @return aspect
     */
    @Bean
    @ConditionalOnBean({OperatorLogFrameworkService.class, ICurrentUserService.class})
    public OperatorLogAspect operatorLogAspect(OperatorLogConfig operatorLogConfig,
                                               OperatorLogFrameworkService service,
                                               ICurrentUserService currentUserService) {
        ObjectMapper objectMapper = new ObjectMapper();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        SimpleModule module = new SimpleModule();

        // 添加脱敏序列化器
        module.addSerializer(new MapDesensitizeSerializer(operatorLogConfig.getDesensitize(),
                operatorLogConfig.getIgnore()));
        module.setSerializerModifier(new DesensitizeSerializerModifier(operatorLogConfig.getDesensitize(), operatorLogConfig.getIgnore()));

        objectMapper.registerModule(module);

        // 脱敏字段注解 已通过@Desensitize#@JsonSerialize(using = ObjectDesensitizeSerializer.class)注解实现

        // 设置参数到工具类中
        OperatorLogs.setObjectMapper(objectMapper);

        // 设置参数到工具类中
        OperatorLogModelBuilder.setObjectMapper(objectMapper);
        OperatorLogModelBuilder.setOperatorLogConfig(operatorLogConfig);
        return new OperatorLogAspect(service, currentUserService);
    }
}
