package com.tesla.cloud.common.enums;


import com.tesla.cloud.common.model.InventoryLog;
import com.tesla.cloud.common.model.OperationLog;
import com.tesla.cloud.common.model.TransactionLog;

public enum LogType {

    OPREATION("operation","操作日志", OperationLog.class),
    INVENTORY("inventory","库存日志", InventoryLog.class),
    TRANSACTION("transaction","交易日志",TransactionLog.class);

    private String value;
    private String cnValue;
    private Class entityClass;

    private LogType(String value, String cnValue, Class cls){
        this.value = value;
        this.cnValue = cnValue;
        this.entityClass = cls;
    }
    public String toCn(){
        return this.cnValue;
    }
    public String toString(){
        return this.value;
    }
    public Class getEntityClass(){return this.entityClass;}
}
