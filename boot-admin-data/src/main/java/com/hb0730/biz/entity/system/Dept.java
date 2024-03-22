package com.hb0730.biz.entity.system;

import com.hb0730.data.entity.DomainEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 部门
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/18
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "sys_dept")
@Entity
public class Dept extends DomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 父类id
     */
    private Integer parentId;

    /**
     * 部门编码
     */
    @NotBlank(message = "部门编码不能为空")
    private String code;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    private String name;

    /**
     * 部门类型 1 公司 2 分公司 3 部门
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 部门负责人
     */
    private String principal;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态
     */
    @Column(name = "is_enabled", columnDefinition = "tinyint(1) default 1")
    private Boolean enabled;
}
