package com.hb0730.basic.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Getter
@Setter
@EqualsAndHashCode
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;
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
