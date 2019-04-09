package com.Kutugin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
@ComponentScan({"com.Kutugin.dao","com.Kutugin.dao.impl.hibernate", "com.Kutugin.services", "com.Kutugin.validators", "com.Kutugin.view"})
public class AppConfig {
    @Bean(name = "br")
    public BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
//    @Bean(name = "clientDao")
//    public ClientDao getClient() {
//        return new ClientEMDao();
//    }
    @Bean(name = "factory")
    public EntityManagerFactory getEntityManagerFactory() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        return factory;
    }
}