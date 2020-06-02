package config;

import controller.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.testApp.api.*;

@Configuration
@EnableWebMvc
public class WebConfig {

    private QuestionnaireDao questionnaireDao;

    private QuestionnaireService questionnaireService;
    private UserService userService;
    private QuestionService questionService;
    private InfoForTeacherService infoForTeacherService;
    private ThemeService themeService;

    private Validator userValidator;


    public WebConfig(QuestionnaireDao questionnaireDao,
    QuestionnaireService questionnaireService, UserService userService, QuestionService questionService,
    InfoForTeacherService infoForTeacherService, ThemeService themeService, Validator userValidator) {
        this.questionnaireDao = questionnaireDao;
        this.questionnaireService = questionnaireService;
        this.userService = userService;
        this.questionService = questionService;
        this.infoForTeacherService = infoForTeacherService;
        this.themeService = themeService;
        this.userValidator = userValidator;
    }


    @Bean
    public UserController userController(){
        return new UserController(questionnaireDao, userValidator,
                userService, questionnaireService, themeService,
                infoForTeacherService, questionService);
    }

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        return resolver;
    }

}
