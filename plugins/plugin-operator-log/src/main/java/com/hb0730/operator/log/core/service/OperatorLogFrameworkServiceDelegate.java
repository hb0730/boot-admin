package com.hb0730.operator.log.core.service;


import com.hb0730.operator.log.core.model.OperatorLogModel;

/**
 * 操作日志框架服务 委托类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
public class OperatorLogFrameworkServiceDelegate implements OperatorLogFrameworkService {
    private final OperatorLogFrameworkService operatorLogFrameworkService;

    public OperatorLogFrameworkServiceDelegate(OperatorLogFrameworkService operatorLogFrameworkService) {
        this.operatorLogFrameworkService = operatorLogFrameworkService;
    }

    @Override
    public void insert(OperatorLogModel log) {
        operatorLogFrameworkService.insert(log);
    }
}
