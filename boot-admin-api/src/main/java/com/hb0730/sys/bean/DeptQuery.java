package com.hb0730.sys.bean;

import com.blinkfox.fenix.specification.annotation.Equals;
import com.blinkfox.fenix.specification.annotation.Like;
import com.hb0730.base.base.BasePageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/18
 */
@Getter
@Setter
@Accessors(chain = true)
public class DeptQuery extends BasePageQuery {

    /**
     * 部门名称
     */
    @Like
    @Parameter(description = "部门名称")
    private String name;
    /**
     * 状态
     */
    @Equals
    @Parameter(description = "状态")
    private Boolean enabled;
}
