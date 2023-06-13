package com.hb0730.boot.admin.modules.sys.system.model.query;

import com.hb0730.boot.admin.data.domain.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统配置查询
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@Schema(description = "系统配置查询")
public class ConfigQuery extends BasePageQuery {
    @Schema(description = "类型 1系统配置 2业务配置")
    private Integer type;
    @Schema(description = "配置项")
    private String code;
}
