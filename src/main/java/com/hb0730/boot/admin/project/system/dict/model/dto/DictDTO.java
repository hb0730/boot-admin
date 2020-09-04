package com.hb0730.boot.admin.project.system.dict.model.dto;

import com.hb0730.boot.admin.domain.model.dto.BusinessDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据字典
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DictDTO extends BusinessDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String description;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;
}
