package com.hzgy.interceptor.annotation;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class AopRegister implements ImportBeanDefinitionRegistrar {

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableControllerAspect.class.getName()));
        String[] scanPackages = attributes.getStringArray("scanPackages");
        int count = 0;
        BeanDefinitionScanner scanner = new BeanDefinitionScanner(registry,false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(RestController.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(RequestMapping.class));
        List<String> beanNames = scanner.getBeanNames(scanPackages);
        for (String beanName : beanNames) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(BeanNameAutoProxyCreator.class);
            builder.addPropertyValue("beanNames", beanName);
            builder.addPropertyValue("interceptorNames", "validatedIntercepter");
            registry.registerBeanDefinition("proxyCreator" + count, builder.getRawBeanDefinition());
            count++;
        }
    }
}
