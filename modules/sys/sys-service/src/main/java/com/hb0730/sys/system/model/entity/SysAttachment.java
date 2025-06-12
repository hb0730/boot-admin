package com.hb0730.sys.system.model.entity;

import com.hb0730.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 附件
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysAttachment extends BaseEntity {
    /**
     * 文件名
     */
    private String displayName;
    /**
     * 文件类型
     */
    private String mediaType;
    /**
     * 文件大小
     */
    private Long size;
    /**
     * 链接
     */
    private String permalink;
}
