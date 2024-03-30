package com.hb0730.rpc.sys.system.domain.query;

import com.blinkfox.fenix.specification.annotation.Equals;
import com.blinkfox.fenix.specification.annotation.Like;
import com.hb0730.commons.BaseQuery;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Getter
@Setter
@Accessors(chain = true)
public class ProductQuery extends BaseQuery {
    @Like
    @Parameter(description = "名称")
    private String name;
    @Equals
    @Parameter(description = "是否启用")
    private Boolean enabled;
}
