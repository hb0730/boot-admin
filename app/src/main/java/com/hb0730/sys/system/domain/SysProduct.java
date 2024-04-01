package com.hb0730.sys.system.domain;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.base.jpa.core.domain.BaseEntity;
import com.hb0730.sys.tenant.domain.TenantPermission;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理端： 产品
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Getter
@Setter
@Entity
@Table(name = "sys_product")
public class SysProduct extends BaseEntity {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    /**
     * 产品对于的菜单与权限
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_product_permission",
            joinColumns = @jakarta.persistence.JoinColumn(name = "product_id"),
            inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "permission_id")
    )
    private List<TenantPermission> permissions;
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
    @Column(name = "`is_enabled`", columnDefinition = "tinyint(1) default 1")
    private Boolean enabled = true;


    /**
     * 获取权限id
     *
     * @return 权限id
     */
    public List<Long> getPermissionIds() {
        if (CollectionUtil.isEmpty(permissions)) {
            return Collections.emptyList();
        }
        return permissions.stream().map(TenantPermission::getId).collect(Collectors.toList());
    }
}
