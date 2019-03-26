package com.Kutugin;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.dao.impl.H2DB.ClientDBDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
@ComponentScan({"com.Kutugin.dao", "com.Kutugin.services", "com.Kutugin.validators", "com.Kutugin.view"})
public class AppConfig {
    @Bean(name = "br")
    public BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
//    @Bean(name = "clientDao")
//    public ClientDao getClient() {
//        return new ClientEMDao();
//    }
    @Bean(name = "clientDao")
    public ClientDao getClient() {
        return new ClientDBDao();
    }
}