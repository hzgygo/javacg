package com.hzgy.user;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.hzgy.interceptor.annotation.EnableControllerAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableApolloConfig
@SpringBootApplication
@EnableDiscoveryClient
@EnableControllerAspect("com.hzgy.user.modules.*")
@EnableTransactionManagement(order = 10)
@ComponentScan(basePackages={"com.hzgy"})
@EnableSwagger2
//@EnableEurekaClient
//@EnableFeignClients(basePackages = "com.hzgy.client")
public class FesUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(FesUserApplication.class, args);
    }

}
