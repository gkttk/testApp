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


    private QuestionnaireService questionnaireService;
    private UserService userService;
    private QuestionService questionService;
    private InfoForTeacherService infoForTeacherService;
    private ThemeService themeService;

    private Validator userValidator;
    private TempNewThemeService tempNewThemeService;


    public WebConfig(QuestionnaireService questionnaireService, UserService userService, QuestionService questionService,
    InfoForTeacherService infoForTeacherService, ThemeService themeService, Validator userValidator, TempNewThemeService tempNewThemeService) {

        this.questionnaireService = questionnaireService;
        this.userService = userService;
        this.questionService = questionService;
        this.infoForTeacherService = infoForTeacherService;
        this.themeService = themeService;
        this.userValidator = userValidator;
        this.tempNewThemeService = tempNewThemeService;
    }


    @Bean
    public UserController userController(){
        return new UserController(userValidator,
                userService, questionnaireService, themeService,
                infoForTeacherService, questionService, tempNewThemeService);
    }

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        return resolver;
    }

}
