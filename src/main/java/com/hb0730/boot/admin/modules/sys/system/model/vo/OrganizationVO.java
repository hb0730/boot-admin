package com.hb0730.boot.admin.modules.sys.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 机构信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/3
 */
@Data
@EqualsAndHashCode
@ToString
public class OrganizationVO implements Serializable {

    /**
     * ID
     */
    @Schema(description = "id")
    protected String id;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    protected String createdBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    protected LocalDateTime created;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    protected String modifiedBy;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    protected LocalDateTime modified;
    /**
     * 父类
     */
    @Schema(description = "父ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "父ID不能为空")
    private String parentId;
    @Schema(description = "所属机构名称")
    private String parentName;
    @Schema(description = "所属机构编码")
    private String parentCode;
    /**
     * 机构代码
     */
    @Schema(description = "机构代码")
    private String code;
    /**
     * 机构名称
     */
    @Schema(description = "机构名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "机构名称不能为空")
    private String name;
    /**
     * 机构path
     */
    @Schema(description = "机构path")
    private String path;
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
     * 省份编码
     */
    @Schema(description = "省份ID")
    private String provinceId;

    /**
     * 省份名称
     */
    @Schema(description = "省份名称")
    private String province;

    /**
     * 城市编码
     */
    @Schema(description = "城市ID")
    private String cityId;

    /**
     * 城市名称
     */
    @Schema(description = "城市名称")
    private String city;

    /**
     * 区县编码
     */
    @Schema(description = "区县ID")
    private String countyId;

    /**
     * 区县名称
     */
    @Schema(description = "区县名称")
    private String county;
    /**
     * 城镇、街道编码
     */
    @Schema(description = "街道ID")
    private String townId;

    /**
     * 城镇、街道名称
     */
    @Schema(description = "街道名称")
    private String town;
    /**
     * 详细地址
     */
    @Schema(description = "详细地址")
    private String address;
    /**
     * 详细地址的经纬度
     */
    @Schema(description = "详细地址的经纬度")
    private String longitude;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String memo;
    /**
     * 是否内置
     */
    @Schema(description = "是否内置")
    private Integer isSystem;

    /**
     * 是否内存
     *
     * @return .
     */
    @Schema(description = "是否内置")
    public boolean isSystem() {
        return isSystem != null && isSystem == 1;
    }

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Integer isEnable;

    /**
     * 是否启用
     *
     * @return .
     */
    public boolean isEnable() {
        return isEnable != null && isEnable == 1;
    }
}
