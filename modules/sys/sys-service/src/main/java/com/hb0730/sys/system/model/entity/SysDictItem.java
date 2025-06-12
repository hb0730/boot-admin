package com.hb0730.sys.system.model.entity;

import com.hb0730.core.entity.BizEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 数据字典项
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDictItem extends BizEntity {

    /**
     * 字典id
     */
    private String dictId;

    /**
     * 字典项文本
     */
    private String itemText;

    /**
     * 字典项值
     */
    private String itemValue;

    /**
     * 额外参数
     */
    private String extra;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private Boolean status = true;
}
