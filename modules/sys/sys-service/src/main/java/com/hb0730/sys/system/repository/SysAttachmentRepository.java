package com.hb0730.sys.system.repository;

import com.hb0730.core.repository.BaseRepository;
import com.hb0730.sys.system.convert.SysAttachmentConvert;
import com.hb0730.sys.system.model.entity.SysAttachment;
import com.hb0730.sys.system.model.request.attachment.SysAttachmentCreateRequest;
import com.hb0730.sys.system.model.request.attachment.SysAttachmentQueryRequest;
import com.hb0730.sys.system.model.vo.SysAttachmentVO;
import com.hb0730.sys.system.repository.mapper.SysAttachmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/21
 */
@Service
@Slf4j
public class SysAttachmentRepository extends BaseRepository<String, SysAttachmentQueryRequest, SysAttachmentVO,
        SysAttachment, SysAttachmentCreateRequest, SysAttachmentCreateRequest, SysAttachmentMapper, SysAttachmentConvert> {
}
