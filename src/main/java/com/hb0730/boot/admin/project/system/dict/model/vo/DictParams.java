package com.hb0730.boot.admin.project.system.dict.model.vo;

import com.hb0730.boot.admin.commons.domain.BusinessDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 数据字段类型
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-30
 */
@Data
public class DictParams implements Serializable {
    /**
     * 是否查询全部
     */
    private Integer isAll;
}
