package com.hb0730.boot.admin.modules.sys.system.model.query;

import com.hb0730.boot.admin.data.domain.BasePageQuery;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户查询参数
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserQuery extends BasePageQuery {
    /**
     * 所属机构
     */
    @Parameter(description = "所属机构")
    private String orgId;
    /**
     * 用户帐号
     */
    @Parameter(description = "用户帐号")
    private String username;
    /**
     * 姓名
     */
    @Parameter(description = "姓名")
    private String nickname;
    /**
     * 联系电话
     */
    @Parameter(description = "联系电话")
    private String phone;
    /**
     * 状态
     */
    @Parameter(description = "状态")
    private Integer enable;
}
