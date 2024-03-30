package com.hb0730.sys.tenant.domain;

import com.hb0730.base.jpa.core.domain.TenantBaseEntity;
import com.hb0730.sys.system.domain.SysProduct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 履约端： 机构
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "bas_organization")
public class TenantOrg extends TenantBaseEntity {
    @Id
    private String id;
    /**
     * 用户
     */
    @OneToMany(mappedBy = "org")
    private List<TenantUser> users;
    /**
     * 机构名称
     */
    @NotBlank(message = "机构名称不能为空")
    private String name;
    /**
     * 父机构ID
     */
    private String parentId;
    /**
     * 联系人
     */
    private String linkMan;
    /**
     * 联系电话
     */
    private String linkTel;
    /**
     * 联系邮箱
     */
    private String linkEmail;
    /**
     * 机构地址
     */
    private String address;
    /**
     * 总部标识
     */
    @Column(name = "`is_system`", columnDefinition = "tinyint(1) default 0")
    private Boolean system;
    /**
     * 是否启用sass
     */
    @Column(name = "`is_saas`", columnDefinition = "tinyint(1)` default 0")
    private Boolean saas;
    /**
     * 产品
     */
    @OneToOne
    @JoinColumn(name = "product_id")
    private SysProduct product;

    /**
     * 使用截止日期
     */
    private Date usedEndTime;

    /**
     * 网点等级 1：一级，2：二级，3：三级
     */
    private Integer level;

    /**
     * 机构路径
     */
    private String path;

    /**
     * 机构类型
     * 1 厂商
     * 2 机构网点
     */
    private Integer type;
    /**
     * 备注
     */
    private String memo;

    /**
     * 是否启用
     */
    @Column(name = "`is_enabled`", columnDefinition = "tinyint(1) default 0")
    private Boolean enabled;
}
