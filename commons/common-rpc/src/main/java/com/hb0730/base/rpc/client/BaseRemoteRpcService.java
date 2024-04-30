package com.hb0730.base.rpc.client;

import com.alipay.sofa.rpc.boot.runtime.param.BoltBindingParam;
import com.alipay.sofa.runtime.api.client.ReferenceClient;
import com.alipay.sofa.runtime.api.client.param.ReferenceParam;
import com.hb0730.base.AppUtil;
import com.hb0730.base.rpc.RpcProperties;
import com.hb0730.base.rpc.SofaRpcClientFactoryBean;
import jakarta.annotation.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * RPC服务基类 用于Client服务,使用BLOT协议
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
public abstract class BaseRemoteRpcService<T> implements IRemoteService<T> {
    private RpcProperties rpcRemoteProperties;
    /**
     * 实体类型
     */
    private Class<T> rpcInterfaceClazz;

    /**
     * 实例
     */
    private T rpcService;

    public BaseRemoteRpcService() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.rpcInterfaceClazz = (Class<T>) parameterizedType[0];
        }
    }

    /**
     * 当前服务类
     *
     * @return .
     */
    protected Class<T> getInterfaceClass() {
        return this.rpcInterfaceClazz;
    }

    /**
     * 应用名称
     *
     * @return 应用名称
     */
    protected abstract String getAppName();

//    /**
//     * 应用名称
//     *
//     * @param which 服务标识
//     * @return 应用名称
//     */
//    protected String getAppName(String which) {
//        return getAppName();
//    }

    /**
     * 获取RPC服务
     *
     * @return RPC服务
     */
    protected String getServer() {
        return getServer(getAppName());
    }

    /**
     * 获取RPC 地址,如果不为空，则采用直连模式，默认blot协议
     *
     * @param which 服务标识
     * @return RPC服务
     */
    @Nullable
    protected String getServer(String which) {
        RpcProperties.Server rpcServer = getRpcServer(which);
        if (null != rpcServer) {
            return rpcServer.getAddress();
        }
        return null;
    }

    /**
     * 获取RPC服务
     *
     * @return RPC服务
     */
    public T getRpcService(String which) {
        if (rpcService == null) {

            String server = getServer(which);
            rpcService = getRpcService(server, getInterfaceClass());
        }
        return rpcService;
    }

    /**
     * 获取RPC服务
     *
     * @return RPC服务
     */
    protected T getRpcService() {
        return getRpcService(getAppName());
    }

    /**
     * 根据接口类型取得RPC接口
     *
     * @param clazz 接口类型
     * @return RPC接口
     */
    protected T getRpcService(Class<T> clazz) {
        if (rpcService == null) {
            rpcService = getRpcService(null, clazz);
        }
        return rpcService;
    }

    /**
     * 根据接口类型取得RPC接口
     *
     * @param server 服务地址
     * @param clazz  接口类型
     * @return RPC接口
     */
    protected T getRpcService(String server, Class<T> clazz) {
        SofaRpcClientFactoryBean clientFactoryBean = AppUtil.getBean(SofaRpcClientFactoryBean.class);
        ReferenceClient referenceClient = clientFactoryBean.getClientFactory().getClient(ReferenceClient.class);
        ReferenceParam<T> referenceParam = new ReferenceParam<>();
        referenceParam.setInterfaceType(clazz);

        //Bolt协议
        BoltBindingParam bindingParam = new BoltBindingParam();
        // 设置服务地址 直连模式
        if (null != server) {
            bindingParam.setTargetUrl(server);
        }
        referenceParam.setBindingParam(bindingParam);
        return referenceClient.reference(referenceParam);
    }

    /**
     * 获取RPC服务配置
     *
     * @return RPC服务配置
     */
    protected RpcProperties.Server getRpcServer(String which) {
        return rpcRemoteProperties.getServices().get(which);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        rpcRemoteProperties = beanFactory.getBean(RpcProperties.class);
    }
}