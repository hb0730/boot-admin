package com.hb0730.rpc.sys.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hb0730.common.api.DomainDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 公告
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/2
 */
@Getter
@Setter
@Slf4j
public class NoticeDto extends DomainDto {
    @Schema(description = "id")
    private String id;

    /**
     * 标题
     */
    @Schema(description = "标题")
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 内容
     */
    @Schema(description = "内容")
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 公告日期（起）
     */
    @Schema(description = "公告日期（起）")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date noticeTimeStart;

    /**
     * 公告日期（止）
     */
    @Schema(description = "公告日期（止）")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date noticeTimeEnd;

    @Schema(description = "是否启用")
    private Boolean enabled;
    /**
     * 机构ID
     */
    @Schema(description = "机构ID,多个用逗号分隔")
    private String orgId;

}
