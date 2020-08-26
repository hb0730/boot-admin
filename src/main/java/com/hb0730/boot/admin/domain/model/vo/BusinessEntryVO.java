package com.hb0730.boot.admin.domain.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class BusinessEntryVO extends BaseVO {
    private Long id;
    private Long parentId;
}
