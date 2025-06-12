package com.hb0730.mybatis.query.doamin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hb0730.base.utils.StrUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/5
 */
@Getter
@Setter
@Schema(description = "分页参数")
public class PageRequest implements Serializable {
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

    @JsonIgnore
    public String getSortsStr() {
        return sorts;
    }

    /**
     * 获取排序
     *
     * @return 排序
     */
    @Nullable
    @JsonIgnore
    public List<SortMate> getSorts() {
        if (StrUtil.isBlank(sorts)) {
            return null;
        }
        return StrUtil.split(sorts, ",").stream().map(sort -> {
            List<String> fileSort = StrUtil.split(sort, " ");
            if (fileSort.size() == 1) {
                fileSort.add("asc");
            }
            String filed = fileSort.get(0);
            String order = fileSort.get(1);
            return new SortMate(filed, order);
        }).toList();
    }

    /**
     * 排序
     */
    @Data
    public static class SortMate implements Serializable {
        public SortMate(String field, String order) {
            this.field = field;
            this.order = StrUtil.isBlank(order) ? "asc" : order;
        }

        public SortMate() {
        }

        /**
         * 排序字段
         */
        private String field;
        /**
         * 排序方式,[asc,desc]
         */
        private String order;
    }
}
