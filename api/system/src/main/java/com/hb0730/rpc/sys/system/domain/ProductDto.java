package com.hb0730.rpc.sys.system.domain;

import com.hb0730.commons.DomainDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Getter
@Setter
@Accessors(chain = true)
public class ProductDto extends DomainDto {
    /**
     * id
     */
    @Schema(description = "id")
    private Long id;
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
    public List<Long> permissionIds;
}
