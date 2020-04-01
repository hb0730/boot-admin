package com.hb0730.boot.admin.project.system.post.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.commons.domain.BusinessDomain;
import com.hb0730.boot.admin.commons.web.model.BusinessVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统岗位
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemPostVO extends BusinessVO {

    private static final long serialVersionUID=1L;

    /**
     * 备注
     */
    private String description;

    /**
     * id
     */
    private Long id;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 岗位英文名称
     */
    private String enname;

    /**
     * 岗位编码
     */
    private String number;

    /**
     * 排序
     */
    private Integer sort;
}
