package com.hb0730.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Data
@EqualsAndHashCode
@TableName("bas_role_permission")
@AllArgsConstructor
@NoArgsConstructor
public class BasRolePermission implements Serializable {
    @TableId
    private String roleId;
    @TableId
    private String permissionId;
}
