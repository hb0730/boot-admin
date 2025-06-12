package com.hb0730.operator.log.core.model;

import com.hb0730.operator.log.core.enums.OperatorRiskLevelEnums;
import lombok.Getter;

/**
 * 操作类型定义
 * <p>
 * 因为枚举需要实现 注解中不可以使用 则需要使用对象
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
@Getter
public class OperatorType {
    /**
     * 风险等级
     */
    private final OperatorRiskLevelEnums riskLevel;

    /**
     * 模块
     */
    private String module;

    /**
     * 类型
     */
    private final String type;

    /**
     * 模板
     */
    private final String template;

    public OperatorType(OperatorRiskLevelEnums riskLevel, String type, String template) {
        this(riskLevel, null, type, template);
    }

    public OperatorType(OperatorRiskLevelEnums riskLevel, String module, String type, String template) {
        this.riskLevel = riskLevel;
        this.module = module;
        this.type = type;
        this.template = template;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
