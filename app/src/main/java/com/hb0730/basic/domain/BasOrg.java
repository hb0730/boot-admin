package com.hb0730.basic.domain;

import com.hb0730.base.jpa.core.domain.TenantBaseEntity;
import com.hb0730.base.jpa.core.id.IdGenerator;
import com.hb0730.sys.system.domain.SysProduct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "bas_organization")
public class BasOrg extends TenantBaseEntity {
    @Id
    @IdGenerator
    private String id;
    /**
     * 产品
     */
    @OneToOne
    @JoinColumn(name = "product_id")
    private SysProduct product;
    /**
     * 上级机构
     */
    private String parentId;
    /**
     * 机构名称
     */
    @NotBlank(message = "机构名称不能为空")
    private String name;
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
     * 到期时间
     */
    private Date usedEndTime;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 路径
     */
    private String path;
    /**
     * 机构类型
     * 1 商户
     * 2 机构
     * 3 部门
     */
    private Integer type = 2;
    /**
     * 备注
     */
    private String memo;
    /**
     * 是否系统机构
     */
    @Column(name = "`is_system`", columnDefinition = "bit(1) default 0")
    private Boolean system = false;
    /**
     * 是否启用
     */
    @Column(name = "`is_enabled`", columnDefinition = "bit(1) default 1")
    private Boolean enabled = true;
    /**
     * 是否启用saas
     */
    @Column(name = "`is_saas`", columnDefinition = "bit(1) default 0")
    private Boolean saas = false;
}
