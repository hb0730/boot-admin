package com.hb0730.boot.admin.project.system.option.model.dto;

import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * options选项
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OptionDTO extends BaseDTO {

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
     * key
     */
    private String optionKey;

    /**
     * value
     */
    private String optionValue;
}
