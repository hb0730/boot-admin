package com.hb0730.rpc.basic.domain.query;

import com.hb0730.common.api.BaseQuery;
import com.hb0730.jpa.specification.annotation.Equals;
import com.hb0730.jpa.specification.annotation.Like;
import com.hb0730.jpa.specification.annotation.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/1
 */
@Getter
@Setter
@Accessors(chain = true)
public class BasUserQuery extends BaseQuery {
    /**
     * 商户
     */
    @Schema(description = "商户", hidden = true)
    @Equals
    private String sysCode;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @Equals
    private String username;
    /**
     * 昵称
     */
    @Schema(description = "昵称")
    @Like
    private String nickname;
    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    @Equals
    private String linkTel;
    /**
     * 状态
     */
    @Equals
    @Schema(description = "状态")
    private Boolean enabled;

    /**
     * 机构ID
     */
    @Schema(description = "机构id")
    @Query(value = "id", joinName = "org", join = Query.Join.LEFT)
    private String orgId;
}
