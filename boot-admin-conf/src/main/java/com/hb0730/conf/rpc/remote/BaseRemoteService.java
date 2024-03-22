package com.hb0730.conf.rpc.remote;

import com.alipay.sofa.rpc.boot.runtime.param.BoltBindingParam;
import com.alipay.sofa.runtime.api.client.ReferenceClient;
import com.alipay.sofa.runtime.api.client.param.ReferenceParam;
import com.hb0730.base.AppUtil;
import com.hb0730.conf.rpc.RpcRemoteProperties;
import com.hb0730.conf.rpc.SofaRpcClientFactoryBean;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * RPC服务基类 用于Client服务,使用BLOT协议
 *
 * @param <T> 接口类型
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/5
 */
public abstract class BaseRemoteService<T> implements IRemoteService<T> {
    @Setter
    private RpcRemoteProperties rpcRemoteProperties;
    /**
     * 实体类型
     */
    private Class<T> rpcInterfaceClazz;

    /**
     * 实例
     */
    private T rpcService;

    public BaseRemoteService() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.rpcInterfaceClazz = (Class<T>) parameterizedType[0];
        }
    }

    @Override
    public T getRpcService() {
        if (rpcService == null) {
            rpcService = getRpcService(rpcInterfaceClazz);
        }
        return rpcService;
    }

    /**
     * 根据接口类型取得RPC接口
     *
     * @param clazz 接口类型
     * @return RPC接口
     */
    protected T getRpcService(Class<T> clazz) {
        SofaRpcClientFactoryBean clientFactoryBean = AppUtil.getBean(SofaRpcClientFactoryBean.class);
        ReferenceClient referenceClient = clientFactoryBean.getClientFactory().getClient(ReferenceClient.class);
        ReferenceParam<T> referenceParam = new ReferenceParam<>();
        referenceParam.setInterfaceType(clazz);


        //Bolt协议
        BoltBindingParam bindingParam = new BoltBindingParam();
        // 设置服务地址 直连模式
        RpcRemoteProperties.Service service = getRpcRemoteProperties().getService();
        String server = service.getBoltServer();
        bindingParam.setTargetUrl(server);

        referenceParam.setBindingParam(bindingParam);

        return referenceClient.reference(referenceParam);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        rpcRemoteProperties = beanFactory.getBean(RpcRemoteProperties.class);
    }


    public RpcRemoteProperties getRpcRemoteProperties() {
        if (rpcRemoteProperties == null) {
            throw new IllegalArgumentException("rpcRemoteProperties is null");
        }
        return rpcRemoteProperties;
    }
}
