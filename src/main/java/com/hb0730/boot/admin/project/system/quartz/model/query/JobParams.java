package com.hb0730.boot.admin.project.system.quartz.model.query;

import com.hb0730.boot.admin.domain.model.query.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * job检索条件
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class JobParams extends BaseParams {
    private String name;
    private String group;
    private Integer isEnabled;
}
