package com.hb0730.sys.system.model.request.user;

import com.hb0730.mybatis.query.annotation.Equals;
import com.hb0730.mybatis.query.annotation.Like;
import com.hb0730.mybatis.query.doamin.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户查询")
public class SysUserQueryRequest extends PageRequest {

    @Like
    @Schema(description = "账号")
    private String username;

    @Like
    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "性别")
    @Equals
    private Integer gender;

    @Schema(description = "状态")
    @Equals
    private Boolean status;

    @Schema(description = "邮箱")
    @Like
    private String email;

    @Schema(description = "手机号")
    @Like
    private String phone;
}
