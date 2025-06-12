package com.hb0730.sys.system.service;

import com.hb0730.core.service.BaseService;
import com.hb0730.sys.system.model.entity.SysAttachment;
import com.hb0730.sys.system.model.request.attachment.SysAttachmentCreateRequest;
import com.hb0730.sys.system.model.request.attachment.SysAttachmentQueryRequest;
import com.hb0730.sys.system.model.vo.SysAttachmentVO;
import com.hb0730.sys.system.repository.SysAttachmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 附件
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/20
 */
@Service
@Slf4j
public class SysAttachmentService extends BaseService<String, SysAttachmentQueryRequest, SysAttachmentVO,
        SysAttachment, SysAttachmentCreateRequest, SysAttachmentCreateRequest, SysAttachmentRepository> {
}
