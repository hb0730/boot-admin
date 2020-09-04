package com.hb0730.boot.admin.project.system.dict.model.dto;

import com.hb0730.boot.admin.domain.model.dto.BusinessDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "字典名称不为空")
    private String name;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不为空")
    private String type;
}
