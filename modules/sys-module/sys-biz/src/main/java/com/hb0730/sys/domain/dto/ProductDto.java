package com.hb0730.sys.domain.dto;

import com.hb0730.mybatis.core.domain.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDto extends BaseDto {
    /**
     * id
     */
    @Schema(description = "id")
    private String id;
    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;
    /**
     * 编码
     */
    @Schema(description = "编码")
    private String code;
    /**
     * 负责人
     */
    @Schema(description = "负责人")
    private String principal;
    /**
     * 联系方式
     */
    @Schema(description = "联系方式")
    private String contact;
    /**
     * 站点数量
     */
    @Schema(description = "站点数量")
    private Integer siteNum;
    /**
     * 账号数量
     */
    @Schema(description = "账号数量")
    private Integer accountNum;
    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean enabled;

    /**
     * 权限ID
     */
    @Schema(description = "权限ID")
    public List<String> permissionIds;
}
