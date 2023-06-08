package com.hb0730.boot.admin.modules.sys.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/8
 */
@Data
@EqualsAndHashCode
@ToString
public class UserVO implements Serializable {
    /**
     * ID
     */
    @Schema(description = "id")
    protected String id;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    protected String createdBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    protected LocalDateTime created;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    protected String modifiedBy;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    protected LocalDateTime modified;
    /**
     * 用户名
     */
    @Schema(description = "用户帐号")
    private String username;
    /**
     * 用户密码
     */
    @Schema(description = "用户密码")
    private String password;
    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String nickname;
    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;
    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Integer enabled;

    /**
     * 是否启用
     *
     * @return .
     */
    @Schema(description = "是否启用")
    public boolean isEnabled() {
        if (null == enabled) {
            return false;
        }
        return enabled == 1;
    }

    /**
     * 所属机构
     */
    @Schema(description = "所属机构")
    private String orgId;
    /**
     * 机构code
     */
    @Schema(description = "机构code")
    private String orgCode;
    /**
     * 机构名称
     */
    @Schema(description = "机构名称")
    private String orgName;
    /**
     * 是否网点管理员
     */
    @Schema(description = "是否网点管理员")
    private Integer isManage;
}
