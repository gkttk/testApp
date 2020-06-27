package com.github.gkttk.testApp.config;

import com.github.gkttk.testApp.*;
import com.github.gkttk.testApp.api.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.github.gkttk.testApp.repositories")
public class DaoConfig {

    private final SessionFactory sessionFactory;

    public DaoConfig(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Bean
    public AnswerDao answerDao(){
        return new AnswerDaoImpl(sessionFactory);
    }

    @Bean
    public InfoForTeacherDao infoForTeacherDao(){
        return new InfoForTeacherDaoImpl(sessionFactory);
    }

    @Bean
    public QuestionDao questionDao(){
        return new QuestionDaoImpl(sessionFactory);
    }

    @Bean
    public QuestionnaireDao questionnaireDao(){
        return new QuestionnaireDaoImpl(sessionFactory);
    }

    @Bean
    public ThemeDao themeDao(){
        return new ThemeDaoImpl(sessionFactory);
    }

    @Bean
    public UserDao userDao(){
        return new UserDaoImpl(sessionFactory);
    }

    @Bean
    public UserDetailsDao userDetailsDao(){
        return new UserDetailsDaoImpl(sessionFactory);
    }

    @Bean
    public TempNewThemeDao tempNewThemeDao(){
        return new TempNewThemeImpl(sessionFactory);
    }

}
