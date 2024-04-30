package com.hb0730.sys.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hb0730.common.mybatis.tenant.core.BaseTenantDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TenantDto extends BaseTenantDto {
    @Schema(description = "id")
    private String id;
    /**
     * 机构名称
     */
    @NotBlank(message = "机构名称不能为空")
    @Schema(description = "机构名称")
    private String name;
    /**
     * 父机构ID
     */
    @Schema(description = "父机构ID")
    private String parentId;
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
     * 总部标识
     */
    @Schema(description = "总部标识")
    private Boolean system = true;
    /**
     * 是否启用sass
     */
    @Schema(description = "是否启用sass")
    private Boolean saas;
    /**
     * 产品
     */
    @Schema(description = "产品")
    private ProductSmallDto product;

    /**
     * 使用截止日期
     */
    @Schema(description = "使用截止日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date usedEndTime;

    /**
     * 网点等级 1：一级，2：二级，3：三级
     */
    @Schema(description = "网点等级 1：一级，2：二级，3：三级")
    private Integer level;

    /**
     * 机构路径
     */
    @Schema(description = "机构路径")
    private String path;

    /**
     * 机构类型
     * 1 厂商
     * 2 机构网点
     */
    @Schema(description = "机构类型")
    private Integer type;
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
