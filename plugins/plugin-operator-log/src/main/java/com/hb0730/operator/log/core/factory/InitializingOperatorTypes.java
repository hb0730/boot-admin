package com.hb0730.operator.log.core.factory;


import com.hb0730.operator.log.core.annotation.OperatorModule;
import com.hb0730.operator.log.core.model.OperatorType;

/**
 * 操作类型初始化器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
public abstract class InitializingOperatorTypes implements OperatorTypeDefinition {


    @Override
    public void afterPropertiesSet() throws Exception {
        OperatorModule moduleDefinition = this.getClass().getAnnotation(OperatorModule.class);
        if (null == moduleDefinition) {
            return;
        }
        OperatorType[] types = this.types();
        if (null == types || types.length == 0) {
            return;
        }
        // 定义类型
        String module = moduleDefinition.value();
        for (OperatorType type : types) {
            type.setModule(module);
            OperatorTypeHolder.set(type);
        }
    }
}
