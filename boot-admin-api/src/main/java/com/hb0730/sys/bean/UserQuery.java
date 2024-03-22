package com.hb0730.sys.bean;

import com.blinkfox.fenix.specification.annotation.Equals;
import com.blinkfox.fenix.specification.annotation.Like;
import com.hb0730.base.base.BasePageQuery;
import com.hb0730.base.specification.annotation.Query;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/19
 */
@Setter
@Getter
@Accessors(chain = true)
public class UserQuery extends BasePageQuery {
    /**
     * 用户账号
     */
    @Equals
    @Parameter(description = "用户账号")
    private String username;
    /**
     * 用户昵称
     */
    @Like
    @Parameter(description = "用户昵称")
    private String nickname;
    /**
     * 部门id
     */
    @Parameter(description = "部门id")
    @Query(value = "id", joinName = "dept", join = Query.Join.LEFT)
    private Integer deptId;

    /**
     * 是否启用
     */
    @Equals
    @Parameter(description = "是否启用")
    private Boolean enabled;
}
