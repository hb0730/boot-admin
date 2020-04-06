package com.hb0730.boot.admin.project.monitor.job.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hb0730.boot.admin.commons.domain.BusinessDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 定时任务 
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_job")
public class SystemJobEntity extends BusinessDomain {

    private static final long serialVersionUID=1L;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * number
     */
    @TableField("number")
    private String number;

    /**
     * name
     */
    @TableField("name")
    private String name;

    /**
     * 调用目标
     */
    @TableField("bean_name")
    private String beanName;

    /**
     * 调用方法
     */
    @TableField("method_name")
    private String methodName;

    /**
     * 参数
     */
    @TableField("params")
    private String params;

    /**
     * 表达式
     */
    @TableField("cron")
    private String cron;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String NUMBER = "number";

    public static final String NAME = "name";

    public static final String BEAN_NAME = "bean_name";

    public static final String METHOD_NAME = "method_name";

    public static final String PARAMS = "params";

    public static final String CRON = "cron";

}
