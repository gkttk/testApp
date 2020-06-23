package controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.testApp.*;
import org.testApp.api.*;
import org.testApp.enums.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.ContentHandler;
import java.util.List;

@Controller
@RequestMapping
public class UserController {

    private Validator userValidator;
    private UserService userService;
    private QuestionnaireService questionnaireService;
    private ThemeService themeService;


    public UserController(Validator userValidator, UserService userService,
                          QuestionnaireService questionnaireService, ThemeService themeService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.questionnaireService = questionnaireService;
        this.themeService = themeService;
    }


    @GetMapping("/")
    public String welcome(){
        return "redirect:/login";
    }


    @PostMapping("/checkLoginAuth")
    public String checkLoginAuth(HttpServletRequest request) {
        String userLogin = request.getParameter("login");
        if (userValidator.checkLoginInDB(userLogin)) {
            return "forward:/validationPassword/";
        }
        request.setAttribute("UserNotExistsMessage", "Такой пользователь не зарегистрирован");
        return "indexPage";
    }

    @PostMapping("/validationPassword")
    public String validationPassword(HttpServletRequest request) {
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        if (userValidator.checkUserPassword(userLogin, userPassword)) {
            return "forward:/userInSession/";
        }
        request.setAttribute("UserWrongPasswordMessage", "Неправильный пароль");
        return "indexPage";
    }

    @PostMapping("/userInSession")
    public String addUserInSession(HttpServletRequest request, HttpSession session) {
        String login = request.getParameter("login");
        User authUser = userService.getUserByLogin(login);
        session.setAttribute("authUser", authUser);
        return "redirect:/addThemeNamesInSession/";
    }

    @GetMapping("/addThemeNamesInSession")
    public String addThemeNamesInSession(HttpSession session) {
        List<Theme> themes = themeService.getAllThemes();
        session.setAttribute("allThemes", themes);
        return "redirect:/facade/";
    }

    @GetMapping("/facade")
    public String facade(HttpSession session) {
        User authUser = (User) session.getAttribute("authUser");
        if (authUser.getRole().equals(Role.STUDENT)) {
            return "redirect:/addQForStudent/";
        } else if (authUser.getRole().equals(Role.TEACHER)) {
            return "redirect:/getResultForTeacher/";
        }
        return "redirect:/loadUsers/";
    }

    @GetMapping("/addQForStudent")
    public String addQuestionnairesForStudent(HttpSession session) {
        User authUser = (User) session.getAttribute("authUser");
        int authUserId = authUser.getId();
        List<Questionnaire> questionnaires = questionnaireService.getQuestionnairesForStudent(authUserId);
        session.setAttribute("studentQuestionnairesList", questionnaires);
        return "redirect:/getThemeNames/";
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

       /* User authUser = (User) session.getAttribute("authUser");
        if (authUser.getRole().equals(Role.STUDENT)) {
            return "student";
        } else if (authUser.getRole().equals(Role.TEACHER)) {
            return "teacher";
        }
        return "admin";*/
       return "redirect:/user";
    }


    @GetMapping("/changeOwnData")
    public String changeOwnData(HttpServletRequest request, HttpSession session) {
        String newPassword = request.getParameter("newPassword");
        String newEmail = request.getParameter("newEmail");
        String newName = request.getParameter("newName");
        String newSurname = request.getParameter("newSurname");
        String newAge = request.getParameter("newAge");

        User oldAuthUser = (User) session.getAttribute("authUser");
        if (!newPassword.equals("")) {
            userService.changePassword(newPassword, oldAuthUser);
        }
        if (!newEmail.equals("")) {
            userService.changeEmail(newEmail, oldAuthUser);
        }
        String userLogin = oldAuthUser.getLogin();
        User authUser = userService.getUserByLogin(userLogin);
        if (!newName.equals("")) {
            authUser.getuDetails().setName(newName);
        }
        if (!newSurname.equals("")) {
            authUser.getuDetails().setSurname(newSurname);
        }
        if (!newAge.equals("")) {
            authUser.getuDetails().setAge(Integer.parseInt(newAge));
        }
        userService.updateUser(authUser);
        session.setAttribute("authUser", authUser);

        if (authUser.getRole().equals(Role.STUDENT)) {
            return "student";
        } else if (authUser.getRole().equals(Role.TEACHER)) {
            return "teacher";
        }
        return "admin";
    }





   @GetMapping("/changeOwnDataP")
    public String changeOwnDataP(){
        return "changeOwnDataPage";
    }



@GetMapping("/user")
    public String user(){
    User authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
   // User authUser = (User) session.getAttribute("authUser");
    if (authUser.getRole().equals(Role.STUDENT)) {
        return "student";
    } else if (authUser.getRole().equals(Role.TEACHER)) {
        return "teacher";
    }
    return "admin";
}





}
