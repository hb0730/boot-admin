package com.hb0730.sys.system.repository;

import com.hb0730.core.repository.BaseRepository;
import com.hb0730.sys.system.convert.SysOperatorLogConvert;
import com.hb0730.sys.system.model.entity.SysOperatorLog;
import com.hb0730.sys.system.model.request.log.SysOperatorLogCreateRequest;
import com.hb0730.sys.system.model.request.log.SysOperatorLogQueryRequest;
import com.hb0730.sys.system.model.vo.SysOperatorLogVO;
import com.hb0730.sys.system.repository.mapper.SysOperatorLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/21
 */
@Service
@Slf4j
public class SysOperatorLogRepository extends BaseRepository<String, SysOperatorLogQueryRequest, SysOperatorLogVO,
        SysOperatorLog, SysOperatorLogCreateRequest, SysOperatorLogCreateRequest, SysOperatorLogMapper, SysOperatorLogConvert> {
}
