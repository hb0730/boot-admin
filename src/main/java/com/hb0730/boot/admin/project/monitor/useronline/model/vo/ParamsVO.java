package com.hb0730.boot.admin.project.monitor.useronline.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class ParamsVO implements Serializable {
    /**
     * 用户登录
     */
    private String username;

    /**
     * 登录id
     */
    private String ipaddr;
}
