package com.hzgy.interceptor.annotation;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class BeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    public BeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry,useDefaultFilters);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return true;
    }

    public List<String> getBeanNames(String... basePackages) {
        List<String> beanNames = new ArrayList<>();
        BeanNameGenerator generator = new AnnotationBeanNameGenerator();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition definition : candidates) {
                beanNames.add(generator.generateBeanName(definition, this.getRegistry()));
            }
        }
        return beanNames;
    }
}
