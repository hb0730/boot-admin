package com.hb0730.rpc.sys.system.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Getter
@Setter
@Accessors(chain = true)
public class RoleSmallDto implements Serializable {
    @Schema(description = "角色ID")
    private Long id;
    @Schema(description = "角色名称")
    private String name;
}
