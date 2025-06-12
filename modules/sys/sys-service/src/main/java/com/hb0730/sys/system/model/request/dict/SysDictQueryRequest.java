package com.hb0730.sys.system.model.request.dict;

import com.hb0730.mybatis.query.annotation.Equals;
import com.hb0730.mybatis.query.annotation.Like;
import com.hb0730.mybatis.query.annotation.OrEquals;
import com.hb0730.mybatis.query.doamin.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据字典查询
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "数据字典查询")
public class SysDictQueryRequest extends PageRequest {

    @Schema(description = "字典名称/编码")
    @Like(value = "dictName")
    @OrEquals(value = "dictCode")
    private String keyword;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @Equals
    private Boolean status;
}
