package com.Kutugin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
@ComponentScan({"com.Kutugin.dao.impl.hibernate", "com.Kutugin.services.impl", "com.Kutugin.validators", "com.Kutugin.view"})
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
    @Bean(name = "factory")
    public EntityManagerFactory getEntityManagerFactory() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}