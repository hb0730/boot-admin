package com.hb0730.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/16
 */
@Getter
@Setter
public class BaseDto implements Serializable {
    /**
     * 创建者
     */
    @Schema(description = "创建者")
    private String createdBy;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间,格式：yyyy-MM-dd HH:mm:ss", format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;
    /**
     * 修改者
     */
    @Schema(description = "修改者")
    private String modifyBy;
    /**
     * 修改时间
     */
    @Schema(description = "修改时间,格式：yyyy-MM-dd HH:mm:ss", format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modified;

}
