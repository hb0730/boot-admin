package com.hb0730.boot.admin.modules.sys.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.data.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户表
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@TableName
public class SysUser extends BaseEntity {
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 是否启用
     */
    private Integer isEnabled;
    /**
     * 所属机构
     */
    private String orgId;
    /**
     * 是否网点管理员
     */
    private Integer isManage;


    public boolean isManager() {
        return isManage == 1;
    }
}
