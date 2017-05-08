package com.tesla.cloud.core.config.database;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolderThreadLocal = new ThreadLocal<String>();

    public static List<String> dataSourceList = new ArrayList<>();

    public static void setDataSourceType(String dataSourceType) {
        Assert.notNull(dataSourceType, "dataSourceType cannot be null");
        contextHolderThreadLocal.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return contextHolderThreadLocal.get();
    }

    public static void clearDataSourceType(){
        contextHolderThreadLocal.remove();
    }

    public static boolean containsDataSource(String dataSourceId){
        return dataSourceList.contains(dataSourceId);
    }
}
