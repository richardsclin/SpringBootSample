package com.fubon.portal;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.consul.ConditionalOnConsulEnabled;
import org.springframework.cloud.consul.serviceregistry.ConsulAutoServiceRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulAutoServiceRegistrationAutoConfiguration;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnConsulEnabled
@ConditionalOnMissingBean(type= "org.springframework.cloud.consul.discovery.ConsulLifecycle")
@AutoConfigureAfter(ConsulAutoServiceRegistrationAutoConfiguration.class)
public class MyConsulListener implements ApplicationContextAware {

    @Autowired(required=false)
    private ConsulAutoServiceRegistration registration;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (registration != null){
            registration.start();
        }
    }
}