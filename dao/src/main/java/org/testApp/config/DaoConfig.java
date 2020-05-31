package org.testApp.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.testApp.*;
import org.testApp.api.*;

@Configuration
@Import(HibernateConfig.class)
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

}
