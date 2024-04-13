package com.hb0730.rpc.sys.tenant.domain.query;

import com.blinkfox.fenix.specification.annotation.Equals;
import com.blinkfox.fenix.specification.annotation.Like;
import com.hb0730.common.api.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商户查询
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/28
 */
@Accessors(chain = true)
@Getter
@Setter
public class TenantQuery extends BaseQuery {
    /**
     * 厂商名称
     */
    @Like
    @Schema(description = "厂商名称")
    private String name;
    /**
     * 厂商代码
     */
    @Equals
    @Schema(description = "厂商代码")
    private String sysCode;

    /**
     * 机构类型
     * 1 厂商
     */
    @Schema(hidden = true)
    @Equals
    private Integer type = 1;

    /**
     * 是否启用
     */
    @Equals
    @Schema(description = "是否启用")
    public Boolean enabled;
}
