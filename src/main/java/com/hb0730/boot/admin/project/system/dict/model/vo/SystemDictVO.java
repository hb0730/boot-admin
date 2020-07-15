package com.hb0730.boot.admin.project.system.dict.model.vo;

import com.hb0730.boot.admin.commons.utils.convert.InputConverter;
import com.hb0730.boot.admin.commons.web.model.BusinessVO;
import com.hb0730.boot.admin.project.system.dict.model.entity.SystemDictEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据字段类型
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemDictVO extends BusinessVO implements InputConverter<SystemDictEntity> {

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
     * 字典名称
     */
    private String name;

    /**
     * 字典英文名称
     */
    private String enname;

    /**
     * 字典编码
     */
    private String number;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 字典值
     */
    private String dictValue;

    /**
     * 字典标签
     */
    private String dictLabel;

    /**
     * 是否默认
     */
    private Integer isDefeult;

    /**
     * 字典参数
     */
    private String params;

    /**
     * 排序
     */
    private String sort;
}
