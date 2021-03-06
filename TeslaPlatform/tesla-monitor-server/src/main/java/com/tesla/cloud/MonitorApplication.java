package com.tesla.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@EnableTurbineStream
@EnableHystrixDashboard //开启Hystrix监控
@SpringBootApplication
public class MonitorApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringApplication.run(MonitorApplication.class,args));
    }
}
