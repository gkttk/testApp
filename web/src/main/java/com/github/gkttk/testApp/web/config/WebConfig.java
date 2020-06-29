package com.github.gkttk.testApp.web.config;

import com.github.gkttk.testApp.api.*;
import com.github.gkttk.testApp.web.controller.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import java.util.Locale;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private QuestionnaireService questionnaireService;
    private UserService userService;
    private QuestionService questionService;
    private InfoForTeacherService infoForTeacherService;
    private ThemeService themeService;
    private Validator userValidator;
    private TempNewThemeService tempNewThemeService;


    public WebConfig(QuestionnaireService questionnaireService, UserService userService, QuestionService questionService,
                     InfoForTeacherService infoForTeacherService, ThemeService themeService,
                     Validator userValidator, TempNewThemeService tempNewThemeService) {
        this.questionnaireService = questionnaireService;
        this.userService = userService;
        this.questionService = questionService;
        this.infoForTeacherService = infoForTeacherService;
        this.themeService = themeService;
        this.userValidator = userValidator;
        this.tempNewThemeService = tempNewThemeService;
    }

    @Bean
    public AvatarUploadController avatarUploadController(){
        return new AvatarUploadController();
    }

    @Bean
    public ChangeOwnDataController changeOwnDataController(){
        return new ChangeOwnDataController(userService);
    }

    @Bean
    public LoginController loginController(){
        return new LoginController(userService, userValidator);
    }

    @Bean
    public TestController testController() {
        return new TestController(questionnaireService, questionService);
    }

    @Bean
    public RegistrationController registrationController() {
        return new RegistrationController(userValidator, userService);
    }

    @Bean
    public AdminController adminController() {
        return new AdminController(userService, tempNewThemeService, userValidator, questionnaireService);
    }

    @Bean
    public TeacherController teacherController() {
        return new TeacherController(infoForTeacherService, questionnaireService, tempNewThemeService, themeService);
    }

    @Bean
    public UserController userController() {
        return new UserController(questionnaireService, themeService);
    }


    @Bean
    public ViewResolver tilesViewResolver() {
        UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
        tilesViewResolver.setViewClass(TilesView.class);
        return tilesViewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
        return tilesConfigurer;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("windows-1251");
        return messageSource;
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        localeResolver.setCookieName("LocaleCookie");
        localeResolver.setCookieMaxAge(3600);
        return localeResolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(5000000);
        return commonsMultipartResolver;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("assests");
    }

}
