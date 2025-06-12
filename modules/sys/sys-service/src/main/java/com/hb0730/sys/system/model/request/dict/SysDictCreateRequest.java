package com.hb0730.sys.system.model.request.dict;

import com.hb0730.base.utils.JsonUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
@Schema(name = "数据字典创建")
@Data
public class SysDictCreateRequest implements Serializable {
    @NotBlank(message = "字典名称不能为空")
    @Schema(description = "字典名称")
    private String dictName;
    /**
     * 字典编码
     */
    @Schema(description = "字典编码")
    @NotBlank(message = "字典编码不能为空")
    private String dictCode;
    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    private String dictType;
    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean status;

    /**
     * 额外参数
     */
    @Schema(description = "额外参数")
    private List<ExtraParamType> extraParams;

    /**
     * 额外参数
     */
    public String getExtraSchema() {
        if (extraParams == null) {
            return null;
        }
        return JsonUtil.DEFAULT.toJson(extraParams);
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
