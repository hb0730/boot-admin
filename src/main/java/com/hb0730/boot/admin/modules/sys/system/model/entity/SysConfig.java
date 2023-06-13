package com.hb0730.boot.admin.modules.sys.system.model.entity;

import com.hb0730.boot.admin.data.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class SysConfig extends BaseEntity {
    /**
     * 类型
     * <ul>
     *     <li>1. 系统配置</li>
     *     <li>2. 业务配置</li>
     * </ul>
     */
    private Integer type;
    /**
     * 配置项
     */
    private String code;
    /**
     * 配置值
     */
    private String value;
    /**
     * 备注,max 255
     */
    private String memo;
}
