package com.hb0730.operator.log.core.factory;

import com.hb0730.operator.log.core.model.OperatorType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 操作类型定义
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
@Component
public interface OperatorTypeDefinition extends InitializingBean {
    /**
     * 获取操作类型
     *
     * @return 操作类型
     */
    OperatorType[] types();
}
