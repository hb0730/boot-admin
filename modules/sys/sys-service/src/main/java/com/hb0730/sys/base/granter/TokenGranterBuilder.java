package com.hb0730.sys.base.granter;

import com.hb0730.base.exception.BasicException;
import com.hb0730.sys.base.enums.LoginGrantEnums;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 授权工厂
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
@Component
public class TokenGranterBuilder {
    private final Map<String, TokenGranter> granterPool = new ConcurrentHashMap<>();

    public TokenGranterBuilder(Map<String, TokenGranter> granterMap) {
        granterMap.forEach(this::register);
    }

    public void register(String type, TokenGranter granter) {
        granterPool.put(type, granter);
    }


    public TokenGranter getGranter(LoginGrantEnums grantType) {
        if (grantType == null) {
            throw new BasicException("请传递正确的 grantType 参数");
        }
        TokenGranter tokenGranter = granterPool.get(grantType.getCode());
        if (tokenGranter == null) {
            throw new BasicException("不支持的 grantType 参数");
        }
        return tokenGranter;
    }

    /**
     * 默认的授权
     *
     * @return TokenGranter
     */
    public TokenGranter defaultGranter() {
        return granterPool.get(LoginGrantEnums.PASSWORD.getCode());
    }


}
