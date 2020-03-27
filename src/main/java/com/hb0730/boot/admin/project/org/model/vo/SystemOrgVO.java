package com.hb0730.boot.admin.project.org.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.commons.domain.BusinessDomain;
import com.hb0730.boot.admin.commons.web.model.BusinessVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 系统组织
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemOrgVO extends BusinessVO {

    private static final long serialVersionUID=1L;

    /**
     * 备注
     */
    private String description;

    /**
     * id
     */
    private Long id;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 名称
     */
    @NotEmpty(message = "名称不为空")
    private String name;

    /**
     * 英文名称
     */
    private String enname;

    /**
     * 编码
     */
    @NotEmpty(message ="编码不为空")
    private String number;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 负责人
     */
    @NotEmpty(message = "编码不为空")
    private String leader;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 联系邮箱
     */
    private String email;
}
