package com.hb0730.sys.domain.dto;

import com.hb0730.mybatis.core.domain.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TenantSmallDto extends BaseDto {
    @Schema(description = "主键")
    private String id;
    /**
     * 系统编码
     */
    @NotBlank(message = "系统编码不能为空")
    @Schema(description = "商户识别码")
    private String sysCode;
    /**
     * 机构名称
     */
    @NotBlank(message = "机构名称不能为空")
    @Schema(description = "商户名称")
    private String name;
    /**
     * 联系人
     */
    @Schema(description = "联系人")
    private String linkMan;
    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String linkTel;
    /**
     * 联系邮箱
     */
    @Schema(description = "联系邮箱")
    private String linkEmail;
    /**
     * 机构地址
     */
    @Schema(description = "机构地址")
    private String address;
    /**
     * 产品
     */
    @Schema(description = "产品")
    @NotBlank(message = "产品不能为空")
    private String productId;

    /**
     * 使用截止日期
     */
    @Schema(description = "使用截止日期")
    private Date usedEndTime;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String memo;
    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean enabled;
}
