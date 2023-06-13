package com.hb0730.boot.admin.modules.sys.system.model.vo;

import com.hb0730.boot.admin.data.enums.ConfigTypeEnums;
import com.hb0730.boot.admin.data.enums.ValueEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 */
@Data
@EqualsAndHashCode
@ToString
public class ConfigVO implements Serializable {
    @Schema(description = "主键")
    private String id;
    @Schema(description = "类型 1系统配置 2业务配置")
    @NotBlank(message = "类型不能为空")
    @Min(value = 1, message = "类型错误")
    @Max(value = 2, message = "类型错误")
    private Integer type;

    @Schema(description = "类型名称,只作用与响应数据")
    public String getTypeName() {
        if (null == type) {
            return "";
        }
        return ValueEnum.valueToEnum(ConfigTypeEnums.class, type, ConfigTypeEnums.CONFIG_TYPE01).getName();
    }

    @Schema(description = "配置项")
    private String code;
    @Schema(description = "配置值")
    private String value;
    @Schema(description = "备注")
    @Max(value = 255, message = "备注长度不能超过255")
    private String memo;
    /**
     * 创建人
     */
    @Schema(description = "创建人")
    protected String createdBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    protected LocalDateTime created;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    protected String modifiedBy;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    protected LocalDateTime modified;
}
