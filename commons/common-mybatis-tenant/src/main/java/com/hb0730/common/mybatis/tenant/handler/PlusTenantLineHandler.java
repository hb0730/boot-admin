package com.hb0730.common.mybatis.tenant.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.hb0730.base.core.TenantContext;
import com.hb0730.base.core.UserInfo;
import com.hb0730.common.mybatis.tenant.config.TenantProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;

/**
 * 租户处理
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Slf4j
@RequiredArgsConstructor
public class PlusTenantLineHandler implements TenantLineHandler {
    private final TenantProperties tenantProperties;

    @Override
    public Expression getTenantId() {
        UserInfo userInfo = TenantContext.get();
        if (null == userInfo) {
            log.warn("无法获取有效的租户id - sysCode");
            return new NullValue();
        }
        String sysCode = userInfo.getSysCode();
        return new StringValue(sysCode);
    }

    @Override
    public String getTenantIdColumn() {
        return tenantProperties.getTenantColumn();
    }

    @Override
    public boolean ignoreTable(String tableName) {
        return tenantProperties.getIgnoreTables().contains(tableName);
    }
}
