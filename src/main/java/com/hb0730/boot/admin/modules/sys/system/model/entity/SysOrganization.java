package com.hb0730.boot.admin.modules.sys.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.data.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.Transactional;

/**
 * 网点机构
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/3/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Transactional
@TableName("sys_org")
public class SysOrganization extends BaseEntity {
    /**
     * 父机构ID
     */
    private String parentId;
    /**
     * 机构path
     */
    private String path;
    /**
     * 机构代码
     */
    private String code;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 联系人
     */
    private String linkMan;

    /**
     * 联系电话
     */
    private String linkTel;

    /**
     * 省份编码
     */
    private String provinceId;

    /**
     * 省份名称
     */
    private String province;

    /**
     * 城市编码
     */
    private String cityId;

    /**
     * 城市名称
     */
    private String city;

    /**
     * 区县编码
     */
    private String countyId;

    /**
     * 区县名称
     */
    private String county;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 详细地址的经纬度
     */
    private String longitude;

    /**
     * 备注
     */
    private String memo;
    /**
     * 是否内置
     */
    private Integer isSystem;

    /**
     * 是否启用
     */
    private Integer isEnable;
}
