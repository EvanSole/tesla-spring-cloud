package com.tesla.cloud.core.config.mybatis;

import com.github.pagehelper.PageHelper;
import com.tesla.cloud.core.config.database.DynamicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.Properties;

@Configuration
@ConditionalOnClass({ SqlSessionFactory.class, SqlSessionFactoryBean.class })
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MybatisAutoConfiguration implements EnvironmentAware {

    public static final Logger _LOG = LoggerFactory.getLogger(MybatisAutoConfiguration.class);

    private RelaxedPropertyResolver propertyResolver;

    @Autowired(required = false)
    private Interceptor[] interceptors;

    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"mybatis.");
    }

    @Bean(name = "sqlSessionFactory")
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dataSource){
        Resource resource = null;
        try {
            SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
            factory.setDataSource(dataSource); //dataSource
            //mybatis global configuration
            //Assert.state(this.resourceLoader.getResource("classpath:mybatis/mybatis-config.xml"),
            //"Cannot find config location: " + resource + " ,please add config file or check your Mybatis configuration)");
            factory.setConfigLocation(this.resourceLoader.getResource("classpath:mybatis/mybatis-config.xml"));
            // mybatis interceptors
            factory.setPlugins(new Interceptor[]{ pageHelper() });
            // typeAliasesPackage
            factory.setTypeAliasesPackage(propertyResolver.getProperty("typeAliasesPackage"));
            // mapperLocations
            factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(propertyResolver.getProperty("mapperLocations")));
            // typeHandlersPackage
            factory.setTypeHandlersPackage(propertyResolver.getProperty("typeHandlersPackage"));

            return factory.getObject();

        } catch (Exception e){
            _LOG.error("Initialization sqlSessionFactory failed, " + e );
            return null;
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory,ExecutorType.SIMPLE);
    }


    @Bean
    public PageHelper pageHelper() {
        _LOG.info(" Register myBatis plugin the PageHelper. ");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}
