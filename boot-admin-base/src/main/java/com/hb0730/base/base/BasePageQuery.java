package com.hb0730.base.base;

import com.hb0730.base.utils.StrUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 分页查询基类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/14
 */
@Getter
@Setter
public class BasePageQuery implements Serializable {
    /**
     * 操作人
     */
    @Schema(description = "操作人", hidden = true)
    private String operator;
    /**
     * 分页size
     */
    @Schema(description = "分页size,只针对分页查询有效")
    private int size;
    /**
     * 当前页码
     */
    @Schema(description = "当前页码,只针对分页查询有效")
    private int current;

    /**
     * 排序字段,格式 filed1 asc,field2 desc
     */
    @Schema(description = "排序字段,格式 filed1 asc,field2 desc")
    private String sorts;

    /**
     * 获取排序
     *
     * @return 排序
     */
    public Optional<List<Sort.Order>> getSorts() {
        if (StrUtil.isBlank(sorts)) {
            return Optional.empty();
        }
        // 分割 sorts
        String[] split = sorts.split(",");
        return Optional.of(Arrays.stream(split).map(s -> {
            // 分割排序字段 asc desc
            String[] split1 = s.split(" ");
            if (split1.length == 1) {
                return Sort.Order.asc(split1[0]);
            } else {
                return Sort.Order.by(split1[0]).with(Sort.Direction.fromString(split1[1]));
            }
        }).toList());
    }
}
