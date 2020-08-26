package com.hb0730.boot.admin.domain.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 业务单
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class BusinessDomain extends BaseDomain {
    @TableField(value = "id")
    private Long id;

    public static final String ID = "id";
}
