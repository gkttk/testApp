package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.testApp.TempNewTheme;
import org.testApp.User;
import org.testApp.api.QuestionnaireService;
import org.testApp.api.TempNewThemeService;
import org.testApp.api.UserService;
import org.testApp.api.Validator;
import org.testApp.enums.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping
public class AdminController {

    private UserService userService;
    private TempNewThemeService tempNewThemeService;
    private Validator userValidator;
    private QuestionnaireService questionnaireService;

    public AdminController(UserService userService, TempNewThemeService tempNewThemeService,
                           Validator userValidator,
                           QuestionnaireService questionnaireService) {
        this.userService = userService;
        this.tempNewThemeService = tempNewThemeService;
        this.userValidator = userValidator;
        this.questionnaireService = questionnaireService;
    }

    @GetMapping("/loadUsers")
    public String loadUsers(HttpSession session) {
        List<User> users = userService.getUsersList();
        session.setAttribute("usersList", users);
        return "redirect:/loadAllTempNewThemes/";
    }

    @GetMapping("/loadAllTempNewThemes")
    public String loadAllTempNewThemes(HttpSession session) {
        List<TempNewTheme> tempNewThemes = tempNewThemeService.getAllTempNewThemes();
        session.setAttribute("tempNewThemes", tempNewThemes);
        return "redirect:/addQForStudent/";
    }



    @GetMapping("/deleteUser")
    public String deleteUserForAdmin(HttpServletRequest request, HttpSession session) {
        User authUser = (User) session.getAttribute("authUser");
        String login = request.getParameter("deleteUserLogin");
        if (userValidator.checkLoginInDB(login)) {
            User deleteUser = userService.getUserByLogin(login);
            int id = deleteUser.getId();
            questionnaireService.deleteQuestionnaire(id);
            userService.deleteUser(login);
            request.setAttribute("deleteUserMessage", "Пользователь успешно удален");
            return "forward:/loadUsers/";
        } else {
            request.setAttribute("deleteUserMessage", "Такого пользователя не существует");
            if (authUser.getRole().equals(Role.STUDENT)) {
                return "student";
            } else if (authUser.getRole().equals(Role.TEACHER)) {
                return "teacher";
            }
            return "admin";
        }
    }

    @PostMapping("/acceptNewTheme")
    public String acceptNewTheme(HttpServletRequest request) {
        int tempThemeId = Integer.parseInt(request.getParameter("tempThemeId"));
        tempNewThemeService.acceptTempNewTheme(tempThemeId);
        return "redirect:/loadAllTempNewThemes/";
    }


    @PostMapping("/refuseNewTheme")
    public String refuseNewTheme(HttpServletRequest request) {
        int tempThemeId = Integer.parseInt(request.getParameter("tempThemeId"));
        tempNewThemeService.refuseTempNewTheme(tempThemeId);
        return "redirect:/loadAllTempNewThemes/";
    }

}
