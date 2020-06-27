package com.hb0730.boot.admin.oss.configuration.properties;

import com.hb0730.boot.admin.commons.constant.enums.AttachmentTypeEnum;
import org.springframework.lang.Nullable;

/**
 * @author bing_huang
 * @date 2020/06/27 12:57
 * @since V1.0
 */
public interface OssProperties {
    /**
     * 检查是否支持给定类型
     *
     * @param type 附件类型
     * @return true为支持类型
     */
    boolean supportType(@Nullable AttachmentTypeEnum type);
}
