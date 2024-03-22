package com.hb0730.base.api.service;

import com.hb0730.base.api.ApiAuth;
import com.hb0730.base.api.TaskStatus;

/**
 * 对外接口
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/1/23
 */
public interface Api {
    /**
     * 业务逻辑主体
     *
     * @param params 请求参数
     * @return 处理结果（JSON）
     */
    TaskStatus execute(ApiAuth auth, String params);
    //--------------------------------------------------------------------------

    /**
     * 接口名称
     */
    String getName();

    /**
     * 业务分组
     */
    String getGroup();

    /**
     * 业务渠道
     */
    String getChannel();
}
