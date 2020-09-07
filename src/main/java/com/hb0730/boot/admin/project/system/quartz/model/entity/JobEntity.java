package com.hb0730.boot.admin.project.system.quartz.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 定时任务(quartz)
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_job")
public class JobEntity extends BaseDomain {

    private static final long serialVersionUID = 1L;

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
     * 任务名称
     */
    @TableField("name")
    private String name;

    /**
     * 任务组
     */
    @TableField("`group`")
    private String group;

    /**
     * Bean名称
     */
    @TableField("bean_name")
    private String beanName;

    /**
     * 方法名称
     */
    @TableField("bean_method")
    private String beanMethod;

    /**
     * 参数
     */
    @TableField("method_params")
    private String methodParams;

    /**
     * cron表达式
     */
    @TableField("cron")
    private String cron;

    /**
     * 负责人
     */
    @TableField("person_in_charge")
    private String personInCharge;

    /**
     * 负责人邮箱
     */
    @TableField("email")
    private String email;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String GROUP = "group";

    public static final String BEAN_NAME = "bean_name";

    public static final String BEAN_METHOD = "bean_method";

    public static final String METHOD_PARAMS = "method_params";

    public static final String CRON = "cron";

    public static final String STATUS = "status";

    public static final String PERSON_IN_CHARGE = "person_in_charge";

    public static final String EMAIL = "email";

}
