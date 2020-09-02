package com.hb0730.boot.admin.project.system.dept.model.dto;

import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 部门
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String description;

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    @NotBlank(message = "部门名称不为空" )
    private String name;

    /**
     * 负责人
     */
    @NotBlank(message = "部门负责人不为空" )
    private String leader;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 父级
     */
    private Long parentId = -1L;

    /**
     * 排序
     */
    @Max(value = 999, message = "排序最大值为999" )
    @Min(value = 0, message = "排序最小值为0" )
    private Integer sort;

    /**
     * 祖级关系
     */
    private String ancestors;
}
