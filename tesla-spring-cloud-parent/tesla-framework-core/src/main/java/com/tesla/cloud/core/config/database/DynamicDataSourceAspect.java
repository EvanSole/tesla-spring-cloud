package com.tesla.cloud.core.config.database;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    //Before advice
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

    //after advice
    @After("@annotation(dataSource)")
    public void restoreDataSource(JoinPoint point, TargetDataSource dataSource) {
        logger.info("Revert DataSource : {} > {}", dataSource.dbShareField().getValue(), point.getSignature());
        DataSourceContextHolder.clearDataSourceType();
    }

}
