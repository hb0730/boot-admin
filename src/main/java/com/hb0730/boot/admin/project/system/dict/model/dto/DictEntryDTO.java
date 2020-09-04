package com.hb0730.boot.admin.project.system.dict.model.dto;

import com.hb0730.boot.admin.domain.model.dto.BusinessEntryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据字典项
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DictEntryDTO extends BusinessEntryDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String description;
    /**
     * 名称
     */
    private String name;

    /**
     * 字典值
     */
    private String value;

    /**
     * 排序
     */
    private Integer sort;
}
