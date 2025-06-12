package com.hb0730.sys.system.service;

import com.hb0730.core.service.BaseService;
import com.hb0730.operator.log.core.model.OperatorLogModel;
import com.hb0730.sys.system.model.entity.SysOperatorLog;
import com.hb0730.sys.system.model.request.log.SysOperatorLogCreateRequest;
import com.hb0730.sys.system.model.request.log.SysOperatorLogQueryRequest;
import com.hb0730.sys.system.model.vo.SysOperatorLogVO;
import com.hb0730.sys.system.repository.SysOperatorLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 操作日志
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
@Service
@Slf4j
public class SysOperatorLogService extends BaseService<String, SysOperatorLogQueryRequest, SysOperatorLogVO,
        SysOperatorLog, SysOperatorLogCreateRequest, SysOperatorLogCreateRequest, SysOperatorLogRepository> {

    /**
     * 插入操作日志
     *
     * @param model 操作日志
     */
    public void insertOperatorLog(OperatorLogModel model) {
        SysOperatorLog log = repository.getMapstruct().convert(model);
        save(log);
    }
}
