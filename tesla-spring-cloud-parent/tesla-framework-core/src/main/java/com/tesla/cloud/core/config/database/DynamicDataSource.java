package com.tesla.cloud.core.config.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源路由分发器，通过AbstractRoutingDataSource路由determineCurrentLookupKey()方法
 * 获取当前线程的DatabaseType
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}
