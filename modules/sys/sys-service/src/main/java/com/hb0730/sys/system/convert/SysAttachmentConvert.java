package com.hb0730.sys.system.convert;


import com.hb0730.base.mapstruct.BizMapstruct;
import com.hb0730.sys.system.model.entity.SysAttachment;
import com.hb0730.sys.system.model.request.attachment.SysAttachmentCreateRequest;
import com.hb0730.sys.system.model.vo.SysAttachmentVO;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/20
 */
@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysAttachmentConvert extends BizMapstruct<SysAttachmentVO, SysAttachment, SysAttachmentCreateRequest
        , SysAttachmentCreateRequest> {
}
