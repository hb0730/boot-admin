package com.hb0730.boot.admin.modules.sys.monitor.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 系统缓存
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/14
 */
@Data
@EqualsAndHashCode
@ToString
public class CacheVO implements Serializable {
    /**
     * 缓存分类：前缀
     */
    @Schema(description = "缓存分类：前缀")
    private String prefix;

    /**
     * 配置KEY
     */
    @Schema(description = "配置KEY")
    @NotNull(message = "配置KEY不能为空")
    private String key;

    /**
     * 缓存实体JSON字符串
     */
    @Schema(description = "缓存实体JSON字符串")
    private String value;

    /**
     * 缓存名称
     */
    @Schema(description = "缓存名称")
    private String name;

    /**
     * 缓存描述
     */
    @Schema(description = "缓存描述")
    private String desc;

    /**
     * 缓存方式
     */
    @Schema(description = "缓存方式")
    private String type;
}
