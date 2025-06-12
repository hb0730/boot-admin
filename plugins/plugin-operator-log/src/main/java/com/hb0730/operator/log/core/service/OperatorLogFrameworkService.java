package com.hb0730.operator.log.core.service;


import com.hb0730.operator.log.core.model.OperatorLogModel;

/**
 * 操作日志框架服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
public interface OperatorLogFrameworkService {
    /**
     * 记录日志
     *
     * @param log log
     */
    void insert(OperatorLogModel log);
}
