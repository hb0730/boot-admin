package com.hb0730.base.rpc.server;

import com.alipay.sofa.rpc.boot.runtime.param.BoltBindingParam;
import com.alipay.sofa.runtime.api.client.ClientFactory;
import com.alipay.sofa.runtime.api.client.ServiceClient;
import com.alipay.sofa.runtime.api.client.param.BindingParam;
import com.alipay.sofa.runtime.api.client.param.ServiceParam;
import com.hb0730.base.AppUtil;
import com.hb0730.base.rpc.SofaRpcClientFactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * RPC服务基类 用于注册服务,使用BLOT协议
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
public class BaseServerRpcService<T> implements InitializingBean {
    private SofaRpcClientFactoryBean sofaRpcClientFactoryBean;

    /**
     * 注册Service
     *
     * @throws Exception .
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Class<T> clazz = getInterface();
        if (clazz == null) {
            throw new IllegalArgumentException("interface is null");
        }

        if (getSofaRpcClientFactoryBean() == null) {
            throw new IllegalArgumentException("sofaRpcClientFactoryBean is null");
        }
        ClientFactory clientFactory = sofaRpcClientFactoryBean.getClientFactory();
        ServiceClient serviceClient = clientFactory.getClient(ServiceClient.class);

        ServiceParam serviceParam = new ServiceParam();
        serviceParam.setInterfaceType(clazz);
        serviceParam.setInstance(this);


        List<BindingParam> params = new ArrayList<>();

        BindingParam bindingParam = new BoltBindingParam();
        params.add(bindingParam);
        serviceParam.setBindingParams(params);

        serviceClient.service(serviceParam);
    }

    public SofaRpcClientFactoryBean getSofaRpcClientFactoryBean() {
        if (sofaRpcClientFactoryBean == null) {
            sofaRpcClientFactoryBean = AppUtil.getBean(SofaRpcClientFactoryBean.class);
        }
        return sofaRpcClientFactoryBean;
    }

    public void setSofaRpcClientFactoryBean(SofaRpcClientFactoryBean sofaRpcClientFactoryBean) {
        this.sofaRpcClientFactoryBean = sofaRpcClientFactoryBean;
    }

    /**
     * 根据接口类型取得RPC接口
     *
     * @return RPC接口
     */
    @SuppressWarnings("unchecked")
    protected Class<T> getInterface() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            return (Class<T>) parameterizedType[0];
        }
        return null;
    }
}