package com.hb0730.biz.dto.sys.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/19
 */
@Getter
@Setter
public class RoleSmallDto implements Serializable {
    @Schema(description = "角色ID")
    private Integer id;
    @Schema(description = "角色名称")
    private String name;
}
