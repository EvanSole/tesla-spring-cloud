package com.tesla.cloud.core.advice;


import com.tesla.cloud.common.enums.LogType;
import com.tesla.cloud.common.model.InventoryLog;
import com.tesla.cloud.common.model.OperationLog;
import com.tesla.cloud.common.model.TransactionLog;
import com.tesla.cloud.core.annotation.NeedLog;
import com.tesla.cloud.core.context.CoreContextContainer;
import com.tesla.cloud.core.context.NeedLogContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoreBeforeTxCommitAdvice implements AfterReturningAdvice {

    private static final Logger _log = LoggerFactory.getLogger(CoreBeforeTxCommitAdvice.class);


    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        NeedLog needLog = method.getAnnotation(NeedLog.class);
        if (needLog != null) {
            saveLog(needLog);
            CoreContextContainer.clearNeedLogContext();
        }
    }

    private void saveLog(NeedLog needLog){
        LogType logTypes[] = needLog.type();
        NeedLogContext needLogContext = CoreContextContainer.getNeedLogContext();
        if (needLogContext != null && needLogContext.getEntityList().size() > 0) {
            List<Class> typeList = new ArrayList<Class>();
            for (int i = 0; i < logTypes.length; i++) {
                typeList.add(logTypes[i].getEntityClass());
            }
            for (Object obj : needLogContext.getEntityList()) {
                if (obj != null && typeList.contains(obj.getClass())) {
                    Map paramMap = new HashMap<>();
                    if (obj instanceof OperationLog) {
                        paramMap.put("OperationLog",(OperationLog) obj);
                    }
                    else if (obj instanceof InventoryLog) {
                        paramMap.put("InventoryLog",(InventoryLog) obj);
                    }
                    else if (obj instanceof TransactionLog) {
                        paramMap.put("TransactionLog",(TransactionLog) obj);
                    }
                    //发送消息到消息队列
                    _log.info(" Send a message to the queue. ");

                }
            }
        }
    }
}
