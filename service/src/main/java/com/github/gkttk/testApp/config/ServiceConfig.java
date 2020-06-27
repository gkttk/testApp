package com.github.gkttk.testApp.config;

import com.github.gkttk.testApp.*;
import com.github.gkttk.testApp.api.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DaoConfig.class)
public class ServiceConfig {

    private DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Bean
    public AnswerService answerService() {
        return new AnswerServiceImpl(daoConfig.answerDao());
    }

    @Bean
    public InfoForTeacherService infoForTeacherService() {
        return new InfoForTeacherServiceImpl(daoConfig.infoForTeacherDao());
    }

    @Bean
    public QuestionnaireService questionnaireService() {
        return new QuestionnaireServiceImpl(daoConfig.questionnaireDao(), questionService(), themeService(), userService());
    }

    @Bean
    public QuestionService questionService() {
        return new QuestionServiceImpl(daoConfig.questionDao(), answerService());
    }

    @Bean
    public ThemeService themeService() {
        return new ThemeServiceImpl(daoConfig.themeDao());
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(daoConfig.userDao());
    }

    @Bean
    public Validator userValidator() {
        return new UserValidator(daoConfig.userDao());
    }

    @Bean
    public TempNewThemeService tempNewThemeService() {
        return new TempNewThemeServiceImpl(daoConfig.tempNewThemeDao());
    }


}
