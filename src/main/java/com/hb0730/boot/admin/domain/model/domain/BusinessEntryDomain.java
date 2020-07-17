package com.hb0730.boot.admin.domain.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class BusinessEntryDomain extends BaseDomain {
    /**
     * 父类id
     */
    @TableField("parent_id")
    private String parentId;
}
