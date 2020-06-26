package com.hb0730.boot.admin.project.monitor.job.model.vo;

import com.hb0730.boot.admin.commons.web.model.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class JobParams extends BaseParams {

    /**
     * 任务编码
     */
    private String number;

    /**
     * 任务名称
     */
    private String name;
    /**
     * 是否启用
     */
    private Integer enabled;
}
