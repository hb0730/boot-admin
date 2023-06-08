package com.hb0730.boot.admin.data.domain;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 分页查询基类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/12
 */
@Data
@EqualsAndHashCode
@ToString
public class BasePageQuery implements Serializable {
    @Parameter(description = "分页size")
    @Schema(description = "分页size")
    private long size;
    @Parameter(description = "当前页数")
    @Schema(description = "当前页数")
    private long current;
}
