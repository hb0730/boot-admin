package com.hb0730.sys.system.model.request.dict.item;

import com.hb0730.mybatis.query.annotation.Equals;
import com.hb0730.mybatis.query.annotation.Like;
import com.hb0730.mybatis.query.doamin.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "数据字典项查询请求参数")
public class SysDictItemQueryRequest extends PageRequest {
    @Schema(description = "字典id")
    @Equals
    private String dictId;

    /**
     * 字典项名称
     */
    @Schema(description = "字典项名称")
    @Like
    private String itemText;

    /**
     * 字典项值
     */
    @Schema(description = "字典项值")
    @Equals
    private String itemValue;
}
