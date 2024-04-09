package com.hb0730.rpc.basic.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 公告
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/7
 */
@Getter
@Setter
@Accessors
public class BasNoticeDto implements Serializable {
    /**
     * 公告ID
     */
    @Schema(description = "公告ID")
    private String id;
    /**
     * 公告标题
     */
    @Schema(description = "公告标题")
    private String title;
    /**
     * 公告内容
     */
    @Schema(description = "公告内容")
    private String content;
    /**
     * 发布时间
     */
    @Schema(description = "发布时间")
    private String created;
    /**
     * 发布者
     */
    @Schema(description = "发布者")
    private String createdBy;
    /**
     * 是否已读
     */
    @Schema(description = "是否已读")
    private Boolean read;
}
