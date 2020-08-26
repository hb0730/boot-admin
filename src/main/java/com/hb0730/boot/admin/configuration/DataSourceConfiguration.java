package com.hb0730.boot.admin.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Configuration
public class DataSourceConfiguration {
//    /**
//     * 创建quartz数据源
//     *
//     * @return {@link DataSource}数据源
//     */
//    @QuartzDataSource
//    @Qualifier("quartzDataSource")
//    public DataSource quartzDataSource() {
//        DataSourceProperties properties = dataSourceProperties();
//        return createHikariDataSource(properties);
//    }
//
//    @Bean("quartzDataProperties")
//    @ConfigurationProperties(prefix = "spring.quartz.properties.org.quartz.datasource")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    private static HikariDataSource createHikariDataSource(DataSourceProperties properties) {
//        // 创建 HikariDataSource 对象
//        HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//        // 设置线程池名
//        if (StringUtils.hasText(properties.getName())) {
//            dataSource.setPoolName(properties.getName());
//        }
//        return dataSource;
//    }


}
