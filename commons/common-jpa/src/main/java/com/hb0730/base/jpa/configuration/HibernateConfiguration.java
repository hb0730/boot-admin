package com.hb0730.base.jpa.configuration;

import com.hb0730.base.jpa.handler.TenantLineHandler;
import com.hb0730.base.jpa.interceptor.TenantSqlInterceptor;
import com.hb0730.base.tenant.TenantContext;
import lombok.Data;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/1
 */
@Configuration
@EnableConfigurationProperties(HibernateConfiguration.TenantProperties.class)
public class HibernateConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "boot.admin.tenant", name = "enableTenantInterceptor", havingValue = "true", matchIfMissing = true)
    public TenantSqlInterceptor tenantSqlInterceptor(
            TenantProperties tenantProperties
    ) {
        return new TenantSqlInterceptor(
                new TenantLineHandler() {
                    @Override
                    public Expression getTenantId() {
                        TenantContext.UserInfo userInfo = TenantContext.get();
                        if (userInfo == null) {
                            return null;
                        }
                        return new StringValue(userInfo.getSysCode());
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
        );
    }

    /**
     * 租户拦截器
     *
     * @return HibernatePropertiesCustomizer
     */
    @Bean
    @ConditionalOnProperty(prefix = "boot.admin.tenant", name = "enableTenantInterceptor", havingValue = "true", matchIfMissing = true)
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(
            TenantSqlInterceptor tenantSqlInterceptor) {
        return hibernateProperties -> {
            hibernateProperties.put(
                    org.hibernate.cfg.AvailableSettings.STATEMENT_INSPECTOR,
                    tenantSqlInterceptor
            );
        };
    }


    @Data
    @ConfigurationProperties(prefix = "boot.admin.tenant")
    public static class TenantProperties implements Serializable {
        /**
         * 是否启用租户过滤器,
         */
        private boolean enableTenantInterceptor = false;
        /**
         * 非租户表
         */
        private List<String> ignoreTables = List.of(
                "sys_permission",
                "sys_product",
                "sys_product_permission",
                "sys_role",
                "sys_role_permission",
                "sys_user",
                "sys_user_role",
                "bas_user_role",
                "bas_role_permission",
                "bas_permission"
        );

        /**
         * 租户字段
         */
        private String tenantColumn = "sys_code";
    }
}
