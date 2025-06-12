package com.hb0730.sys.system.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hb0730.base.utils.JsonUtil;
import com.hb0730.base.utils.StrUtil;
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
@Schema(name = "数据字典项")
public class SysDictItemVO implements Serializable {
    @Schema(description = "字典项id")
    private String id;
    @Schema(description = "字典id")
    private String dictId;
    @Schema(description = "字典项名称")
    private String itemText;
    @Schema(description = "字典项值")
    private String itemValue;
    @Schema(description = "排序")
    private Integer sort;
    /**
     * 额外参数
     */
    @JsonIgnore
    @Schema(hidden = true)
    private String extra;

    /**
     * 额外参数
     */
    @Schema(description = "额外参数")
    public Map<String, String> getExtraParams() {
        if (StrUtil.isBlank(extra)) {
            return null;
        }
        return JsonUtil.DEFAULT.json2Obj(extra, new TypeReference<Map<String, String>>() {
        });
    }

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean status;
}
