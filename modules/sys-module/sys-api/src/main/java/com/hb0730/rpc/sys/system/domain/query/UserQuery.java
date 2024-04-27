package com.hb0730.rpc.sys.system.domain.query;

import com.hb0730.common.api.BaseQuery;
import com.hb0730.jpa.specification.annotation.Equals;
import com.hb0730.jpa.specification.annotation.Like;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Setter
@Getter
@Accessors(chain = true)
public class UserQuery extends BaseQuery {
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
     * 是否启用
     */
    @Equals
    @Parameter(description = "是否启用")
    private Boolean enabled;
}