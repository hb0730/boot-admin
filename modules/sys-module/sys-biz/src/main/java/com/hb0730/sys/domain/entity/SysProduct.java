package com.hb0730.sys.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.mybatis.core.domain.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString
@TableName("sys_product")
public class SysProduct extends BaseEntity {
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID)
    private String id;
    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * 负责人
     */
    private String principal;
    /**
     * 联系方式
     */
    private String contact;

    /**
     * 站点数量
     */
    private Integer siteNum;
    /**
     * 账号数量
     */
    private Integer accountNum;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    @TableField(value = "`is_enabled`", property = "enabled")
    private Boolean enabled = true;
}
