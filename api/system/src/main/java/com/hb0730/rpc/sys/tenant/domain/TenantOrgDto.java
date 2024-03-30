package com.hb0730.rpc.sys.tenant.domain;

import com.hb0730.commons.TenantDomainDto;
import com.hb0730.rpc.sys.system.domain.ProductDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/28
 */
@Getter
@Setter
@Accessors(chain = true)
public class TenantOrgDto extends TenantDomainDto {
    private String id;
    /**
     * 机构名称
     */
    @NotBlank(message = "机构名称不能为空")
    private String name;
    /**
     * 父机构ID
     */
    private String parentId;
    /**
     * 联系人
     */
    private String linkMan;
    /**
     * 联系电话
     */
    private String linkTel;
    /**
     * 联系邮箱
     */
    private String linkEmail;
    /**
     * 机构地址
     */
    private String address;
    /**
     * 总部标识
     */
    private Boolean system;
    /**
     * 是否启用sass
     */
    private Boolean saas;
    /**
     * 产品
     */
    private ProductDto product;

    /**
     * 使用截止日期
     */
    private Date usedEndTime;

    /**
     * 网点等级 1：一级，2：二级，3：三级
     */
    private Integer level;

    /**
     * 机构路径
     */
    private String path;

    /**
     * 机构类型
     * 1 厂商
     * 2 机构网点
     */
    private Integer type;
    /**
     * 备注
     */
    private String memo;

    /**
     * 是否启用
     */
    private Boolean enabled;
}
