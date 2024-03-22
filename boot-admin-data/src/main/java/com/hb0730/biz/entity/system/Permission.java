package com.hb0730.biz.entity.system;

import com.hb0730.data.entity.DomainEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 菜单与权限-TABLE
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/27
 */
@Getter
@Setter
@Entity
@Table(name = "sys_permission")
public class Permission extends DomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 父类id
     */
    private Integer parentId;


    /**
     * 菜单角色
     */
    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;
    /*==========================路由信息==========================*/
    /**
     * 路由地址
     */
    private String path;
    /**
     * 路由名称（必须保持唯一）
     */
    private String routeName;
    /**
     * 路由重定向
     */
    private String redirect;
    /**
     * 路由组件
     */
    private String component;
    /*==========================meta==========================*/
    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String title;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否展示
     */
    @Column(columnDefinition = "bit(1) default 1")
    private Boolean showLink;
    /**
     * 菜单排序，值越高排的越后（只针对顶级路由）
     */
    @Column(name = "`rank`")
    private Integer rank = 99;
    /**
     * 是否显示父菜单
     */
    @Column(columnDefinition = "bit(1) default 1")
    private Boolean showParent;
    /**
     * 是否缓存
     */
    @Column(columnDefinition = "bit(1) default 0")
    private Boolean keepAlive;
    /**
     * 需要内嵌的iframe链接地址
     */
    private String frameSrc;
    /**
     * 菜单类型 0 菜单 1 iframe 2 外链 3 按钮
     */
    private Integer menuType;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 是否启用
     */
    @Column(name = "is_enabled", columnDefinition = "bit(1) default 1")
    private Boolean enabled;
}
