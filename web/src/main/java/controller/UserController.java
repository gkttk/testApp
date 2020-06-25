package controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.testApp.*;
import org.testApp.api.*;
import org.testApp.enums.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping
public class UserController {

    private QuestionnaireService questionnaireService;
    private ThemeService themeService;

    private final static int MAXRESULTONPAGE = 6;

    public UserController(QuestionnaireService questionnaireService, ThemeService themeService) {
        this.questionnaireService = questionnaireService;
        this.themeService = themeService;
    }


    @GetMapping("/")
    public String welcome() {
        return "redirect:/login";
    }


   /* @PostMapping("/checkLoginAuth")
    public String checkLoginAuth(HttpServletRequest request) {
        String userLogin = request.getParameter("login");
        if (userValidator.checkLoginInDB(userLogin)) {
            return "forward:/validationPassword/";
        }
        request.setAttribute("UserNotExistsMessage", "Такой пользователь не зарегистрирован");
        return "indexPage";
    }*/

    /*@PostMapping("/validationPassword")
    public String validationPassword(HttpServletRequest request) {
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        if (userValidator.checkUserPassword(userLogin, userPassword)) {
            return "forward:/userInSession/";
        }
        request.setAttribute("UserWrongPasswordMessage", "Неправильный пароль");
        return "indexPage";
    }*/



    /*@PostMapping("/userInSession")
    public String addUserInSession(HttpServletRequest request, HttpSession session) {
        String login = request.getParameter("login");
        User authUser = userService.getUserByLogin(login);
        session.setAttribute("authUser", authUser);
        return "redirect:/addThemeNamesInSession/";
    }*/

    @GetMapping("/addThemeNamesInSession")
    public String addThemeNamesInSession(HttpSession session) {
        List<Theme> themes = themeService.getAllThemes();
        session.setAttribute("allThemes", themes);
        return "redirect:/facade";
    }

    @GetMapping("/facade")
    public String facade() {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getRole().equals(Role.STUDENT)) {
            return "redirect:/addQForStudent/";
        } else if (authUser.getRole().equals(Role.TEACHER)) {
            return "redirect:/getResultForTeacher";
        }
        return "redirect:/loadUsers";
    }

    @GetMapping("/addQForStudent")
    public String addQuestionnairesForStudent(HttpSession session, HttpServletRequest request) {
        int userQuestionnairesCurrentPage = 1;
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (request.getParameter("userQuestionnairesCurrentPage") != null) {
            userQuestionnairesCurrentPage = Integer.parseInt(request.getParameter("userQuestionnairesCurrentPage"));
        }
        List<Questionnaire> questionnaires = questionnaireService.getQuestionnairesForUserPagination(authUser.getId(), userQuestionnairesCurrentPage, MAXRESULTONPAGE);
        int resultsCount = questionnaireService.questionnairesForUserCount(authUser.getId());
        int userQuestionnairesPagesCount = (int) Math.ceil((resultsCount * 1.0) / MAXRESULTONPAGE);

        session.setAttribute("userQuestionnaires", questionnaires);
        session.setAttribute("userQuestionnairesPagesCount", userQuestionnairesPagesCount);
        session.setAttribute("userQuestionnairesCurrentPage", userQuestionnairesCurrentPage);

        session.setAttribute("studentQuestionnairesList", questionnaires);

        return "redirect:/getThemeNames";
    }

    @GetMapping("/getThemeNames")
    public String getThemeNames(HttpSession session) {
        int theme_id;
        String themeName;
        List<Questionnaire> questionnaires = (List<Questionnaire>) session.getAttribute("studentQuestionnairesList");
        for (int i = 1; i <= questionnaires.size(); i++) {
            theme_id = questionnaires.get(i - 1).getQuestionnaireTheme().getId();
            themeName = themeService.getThemeName(theme_id);
            session.setAttribute("themeName" + i, themeName);
        }

        return "redirect:/user";
    }


    @GetMapping("/user")
    public String user() {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getRole().equals(Role.STUDENT)) {
            return "student";
        } else if (authUser.getRole().equals(Role.TEACHER)) {
            return "teacher";
        }
        return "admin";
    }


}
