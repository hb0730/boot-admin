package com.hb0730.sys.system.model.request.dict.item;

import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.base.utils.JsonUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Map;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "数据字典项创建请求参数")
public class SysDictItemCreateRequest implements Serializable {
    /**
     * 字典id
     */
    @Schema(description = "字典id")
    private String dictId;

    /**
     * 字典项文本
     */
    @Schema(description = "字典项文本")
    private String itemText;

    /**
     * 字典项值
     */
    @Schema(description = "字典项值")
    private String itemValue;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean status;

    /**
     * 额外参数
     */
    @Schema(description = "额外参数")
    private Map<String, String> extraParams;

    /**
     * 额外参数
     */
    public String getExtra() {
        if (CollectionUtil.isEmpty(extraParams)) {
            return null;
        }
        return JsonUtil.DEFAULT.toJson(extraParams);
    }
}
