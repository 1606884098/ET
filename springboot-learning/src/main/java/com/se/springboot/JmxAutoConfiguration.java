package com.se.springboot;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

import java.util.logging.Logger;

/**
 * @Author Science
 * @Date 2020/4/10 15:17
 * @Version 1.0
 */
public class JmxAutoConfiguration {
    //private Logger LOGGER = LoggerFactory.getLogger(JmxAutoConfiguration.class);

    //@Value("${jmx.rmi.host:localhost}")
    private String rmiHost="192.168.25.131";

    //@Value("${jmx.rmi.port:9999}")
    private Integer rmiPort=9999;

    @Bean
    public RmiRegistryFactoryBean rmiRegistry() {
        final RmiRegistryFactoryBean rmiRegistryFactoryBean = new RmiRegistryFactoryBean();
        rmiRegistryFactoryBean.setPort(rmiPort);
        rmiRegistryFactoryBean.setAlwaysCreate(true);
        //LOGGER.info("RmiRegistryFactoryBean create success !!");
        return rmiRegistryFactoryBean;
    }

    @Bean
    @DependsOn("rmiRegistry")
    public ConnectorServerFactoryBean connectorServerFactoryBean() throws Exception {
        final ConnectorServerFactoryBean connectorServerFactoryBean = new ConnectorServerFactoryBean();
        connectorServerFactoryBean.setObjectName("connector:name=rmi");
        connectorServerFactoryBean.setServiceUrl(
                String.format("service:jmx:rmi://%s:%s/jndi/rmi://%s:%s/jmxrmi", rmiHost, rmiPort, rmiHost, rmiPort));
        //LOGGER.info("ConnectorServerFactoryBean create success !!");
        return connectorServerFactoryBean;
    }

}
