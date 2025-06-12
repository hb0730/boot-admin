package com.hb0730.sys.system.convert;

import com.hb0730.base.mapstruct.BizMapstruct;
import com.hb0730.operator.log.core.model.OperatorLogModel;
import com.hb0730.sys.system.model.entity.SysOperatorLog;
import com.hb0730.sys.system.model.request.log.SysOperatorLogCreateRequest;
import com.hb0730.sys.system.model.vo.SysOperatorLogVO;
import org.mapstruct.Mappings;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysOperatorLogConvert extends BizMapstruct<SysOperatorLogVO, SysOperatorLog,
        SysOperatorLogCreateRequest, SysOperatorLogCreateRequest> {


    @Mappings({
            @org.mapstruct.Mapping(target = "operatorId", source = "userId"),
            @org.mapstruct.Mapping(target = "operator", source = "username"),
            @org.mapstruct.Mapping(target = "traceId", source = "traceId"),
            @org.mapstruct.Mapping(target = "address", source = "address"),
            @org.mapstruct.Mapping(target = "location", source = "location"),
            @org.mapstruct.Mapping(target = "userAgent", source = "userAgent"),
            @org.mapstruct.Mapping(target = "module", source = "module"),
            @org.mapstruct.Mapping(target = "type", source = "type"),
            @org.mapstruct.Mapping(target = "logInfo", source = "logInfo"),
            @org.mapstruct.Mapping(target = "riskLevel", source = "riskLevel"),
            @org.mapstruct.Mapping(target = "extra", source = "extra"),
            @org.mapstruct.Mapping(target = "result", source = "result"),
            @org.mapstruct.Mapping(target = "errorMessage", source = "errorMessage"),
            @org.mapstruct.Mapping(target = "returnValue", source = "returnValue"),
            @org.mapstruct.Mapping(target = "duration", source = "duration"),
            @org.mapstruct.Mapping(target = "startTime", source = "startTime"),
            @org.mapstruct.Mapping(target = "endTime", source = "endTime"),
    })
    SysOperatorLog convert(OperatorLogModel logModel);
}
