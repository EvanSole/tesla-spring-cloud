package com.tesla.cloud;

import com.tesla.cloud.core.config.database.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import(DynamicDataSourceRegister.class)
public class BooksApplication {

    public static void main( String[] args )
    {
        SpringApplication.run(BooksApplication.class, args);
    }

//    @Bean
//    public DynamicDataSourceRegister dynamicDataSourceRegister(){
//        return new DynamicDataSourceRegister();
//    }

}
