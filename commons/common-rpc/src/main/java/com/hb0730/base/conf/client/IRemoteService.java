package com.hb0730.base.conf.client;

import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 远程服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
public interface IRemoteService<T> extends BeanFactoryAware {


    /**
     * 根据接口类型取得RPC接口
     *
     * @return RPC接口
     */
    T getRpcService();
}