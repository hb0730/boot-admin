package com.hb0730.sys.system.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hb0730.base.utils.JsonUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
@Data
@Schema(name = "数据字典")
public class SysDictVO implements Serializable {
    @Schema(description = "主键")
    private String id;

    @Schema(description = "字典类型")
    private String dictType;

    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    private String dictName;

    /**
     * 字典编码
     */
    @Schema(description = "字典编码")
    private String dictCode;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

    /**
     * 状态值 1有效 0无效
     */
    @Schema(description = "状态值 1有效 0无效")
    private Boolean status;
    /**
     * 额外参数
     */
    @JsonIgnore
    @Schema(hidden = true)
    private String extraSchema;

    /**
     * 额外参数
     */
    @Schema(description = "额外参数")
    public List<ExtraParamType> getExtraParams() {
        if (extraSchema == null) {
            return null;
        }
        return JsonUtil.DEFAULT.json2Obj(extraSchema, new TypeReference<List<ExtraParamType>>() {
        });
    }

    /**
     * 额外参数 类型
     */
    @Schema(description = "额外参数 类型")
    @Data
    public static class ExtraParamType implements Serializable {
        /**
         * 类型
         */
        @Schema(description = "类型")
        private String type;
        /**
         * 名称
         */
        @Schema(description = "名称")
        private String name;
    }

}
