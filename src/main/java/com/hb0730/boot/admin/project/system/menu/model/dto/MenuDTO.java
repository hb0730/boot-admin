package com.hb0730.boot.admin.project.system.menu.model.dto;

import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 菜单
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuDTO extends BaseDTO {

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
     * 是否外链
     */
    private Integer external;
    /**
     * iframe菜单
     */
    private Integer iframe;
    /**
     * cache
     */
    private Integer cache;
    /**
     * 是否可见
     */
    private Integer hidden;
    /**
     * 是否18n;
     */
    private Integer i18n;
    /**
     * 名称
     */
    @NotBlank(message = "名称不为空")
    private String title;
    /**
     * 英语名称
     */
    @NotBlank(message = "英文名称不为空")
    private String enname;

    /**
     * 父菜单id
     */
    @NotNull(message = "父id不为空")
    private Long parentId;

    /**
     * 路由地址
     */
    @NotBlank(message = "菜单地址不为空")
    private String path;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 图标
     */
    private String icon;

    /**
     * 展示顺序
     */
    @Min(value = 0, message = "最小只能为0")
    @Max(value = 999, message = "最大为999")
    private Integer sort;

    /**
     * 是否外链
     *
     * @return 是否外链
     */
    public boolean isExternal() {
        return this.external == 1;
    }

    /**
     * 是否显示
     *
     * @return 是否显示
     */
    public boolean isShowLink() {
        return this.hidden == 0;
    }

    /**
     * 是否i18n
     *
     * @return i18n
     */
    public boolean isI18n() {
        return this.i18n == 1;
    }

    /**
     * 是否iframe
     *
     * @return iframe
     */
    public boolean isIframe() {
        return this.iframe == 1;
    }

    /**
     * 是否缓存
     *
     * @return 是否缓存
     */
    public boolean isCache() {
        return this.cache == 1;
    }
}
