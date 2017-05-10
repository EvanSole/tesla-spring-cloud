package com.tesla.cloud.core.config.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class MyBatisMapperScannerConfig implements EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisMapperScannerConfig.class);

    @Value("${mybatis.basePackage}")
    private String mybatisPackages;

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        logger.info("Scanning mapper,you can define multiple paths through the basePackage property.");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(mybatisPackages);
        Properties properties = new Properties();
        //Pay special attention to here, and don't put TeslaMapper basePackage,
        //also is not the same as other Mapper to be scanned
        properties.setProperty("mappers", TeslaMapper.class.getName());
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }


    @Override
    public void setEnvironment(Environment environment) {
        logger.info("Loading mybatis mapperScannerConfigurer.");
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(environment, "mybatis.");
        mybatisPackages = propertyResolver.getProperty("basePackage","com.tesla.cloud.mapper");
        logger.info("mybatis packages : {} " , mybatisPackages );
    }
}
