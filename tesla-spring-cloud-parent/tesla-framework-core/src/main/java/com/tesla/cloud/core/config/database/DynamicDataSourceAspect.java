package com.tesla.cloud.core.config.database;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/***
 * 使用Aspect切换数据源，
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    //前置通知
    @Before("@annotation(dataSource)")
    public void changeDataSource(JoinPoint point, TargetDataSource dataSource) throws Throwable {
        String dataSourceId = dataSource.dbShareField().getValue();
        if (!DataSourceContextHolder.containsDataSource(dataSourceId)) {
            logger.info("The dataSource [{}] does not exist，use the default data source {}", dataSource.dbShareField().getValue(), point.getSignature());
        } else {
            logger.info("Use DataSource : {} > {}", dataSource.dbShareField().getValue(), point.getSignature());
            DataSourceContextHolder.setDataSourceType(dataSource.dbShareField().getValue());
        }
    }

    //后置通知
    @After("@annotation(dataSource)")
    public void restoreDataSource(JoinPoint point, TargetDataSource dataSource) {
        logger.info("Revert DataSource : {} > {}", dataSource.dbShareField().getValue(), point.getSignature());
        DataSourceContextHolder.clearDataSourceType();
    }

}
