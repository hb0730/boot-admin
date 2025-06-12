package com.hb0730.sys.framework;

import com.hb0730.operator.log.core.model.OperatorLogModel;
import com.hb0730.operator.log.core.service.OperatorLogFrameworkService;
import com.hb0730.sys.system.service.SysOperatorLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 操作日志包 实现类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/10
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OperatorLogFrameworkServiceImpl implements OperatorLogFrameworkService {
    private final SysOperatorLogService operatorLogService;

    @Override
    public void insert(OperatorLogModel model) {
        operatorLogService.insertOperatorLog(model);
    }
}
