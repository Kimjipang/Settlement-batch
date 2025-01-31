package com.example.settlement_batch.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.example.settlement_batch.advertisement.repository.read",
                "com.example.settlement_batch.video.repository.read"
        },
        entityManagerFactoryRef = "readEntityManagerFactory",
        transactionManagerRef = "readTransactionManager"
)
//@EntityScan(
//        basePackages = {
//                "com.example.settlement_batch.advertisement.entity",
//                "com.example.settlement_batch.user.entity",
//                "com.example.settlement_batch.video.entity"
//        }
//)
public class ReadEntityManagerConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactory(@Qualifier("readDataSource") DataSource readDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(readDataSource);
        em.setPackagesToScan(
                "com.example.settlement_batch.advertisement.entity",
                "com.example.settlement_batch.user.entity",
                "com.example.settlement_batch.video.entity"
        );
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "none");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public JpaTransactionManager readTransactionManager(
            @Qualifier("readEntityManagerFactory") EntityManagerFactory EntityManagerFactory
    ) {
       return new JpaTransactionManager(EntityManagerFactory);
    }
}
