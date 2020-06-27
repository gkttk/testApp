package com.github.gkttk.testApp.config;

import com.github.gkttk.testApp.*;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;

@Configuration
@Import(SettingsConfig.class)
public class HibernateConfig {

    private final SettingsConfig settingsConfig;

    public HibernateConfig(SettingsConfig settingsConfig) {
        this.settingsConfig = settingsConfig;
    }

    @Bean
    public DataSource dataSource() {
        final DataSourceSettings dataSourceSettings = settingsConfig.dataSourceSettings();

        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(dataSourceSettings.getUrl());
        hikariDataSource.setUsername(dataSourceSettings.getName());
        hikariDataSource.setPassword(dataSourceSettings.getPassword());
        hikariDataSource.setDriverClassName(dataSourceSettings.getDriver());
        hikariDataSource.setMaximumPoolSize(20);
        return hikariDataSource;
    }


    @Bean
    public LocalSessionFactoryBean entityManagerFactory() {  //sessionFactoryBean
        final LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setAnnotatedClasses(User.class, Theme.class, Answer.class, Question.class, Questionnaire.class,
                UserDetails.class, TempNewTheme.class); //вместо localSessionFactoryBean.setPackagesToScan("org.testApp");
        localSessionFactoryBean.setHibernateProperties(settingsConfig.hibernateProperties());

        return localSessionFactoryBean;

    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(entityManagerFactory().getObject());   //sessionFactoryBean
        return transactionManager;
    }




}
