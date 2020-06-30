package com.github.gkttk.testApp.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.gkttk.testApp.TempNewTheme;
import com.github.gkttk.testApp.User;
import com.github.gkttk.testApp.api.QuestionnaireService;
import com.github.gkttk.testApp.api.TempNewThemeService;
import com.github.gkttk.testApp.api.UserService;
import com.github.gkttk.testApp.api.Validator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping
public class AdminController {

    private UserService userService;
    private TempNewThemeService tempNewThemeService;
    private Validator userValidator;
    private QuestionnaireService questionnaireService;

    private String rootAvatarPath = "D:\\Projects\\userAvatars\\";

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
        return "redirect:/loadAllTempNewThemes";
    }

    @GetMapping("/loadAllTempNewThemes")
    public String loadAllTempNewThemes(HttpSession session) {
        List<TempNewTheme> tempNewThemes = tempNewThemeService.getAllTempNewThemes();
        session.setAttribute("tempNewThemes", tempNewThemes);
        return "redirect:/addQForStudent";
    }

    @GetMapping("/deleteUser")
    public String deleteUserForAdmin(HttpServletRequest request) throws IOException {
        String login = request.getParameter("deleteUserLogin");
        if (userValidator.checkLoginInDB(login)) {
            User deleteUser = userService.getUserByLogin(login);
            String avatarName = rootAvatarPath + deleteUser.getLogin() + deleteUser.getEmail() + ".jpg";
            Path path = Paths.get(avatarName);
            if(Files.exists(path)){
                Files.delete(path);
            }
            int id = deleteUser.getId();
            questionnaireService.deleteQuestionnaire(id);
            userService.deleteUser(login);
            request.setAttribute("deleteUserMessage", "Пользователь успешно удален");
            return "forward:/refreshUsers";
        } else {
            request.setAttribute("deleteUserMessage", "Такого пользователя не существует");
            return "forward:/user";
        }
    }

    @GetMapping("/refreshUsers")
    public String refreshUsers(HttpSession session){
        List<User> users = userService.getUsersList();
        session.setAttribute("usersList", users);
        return "forward:/user";
    }

    @PostMapping("/acceptNewTheme")
    public String acceptNewTheme(HttpServletRequest request) {
        int tempThemeId = Integer.parseInt(request.getParameter("tempThemeId"));
        tempNewThemeService.acceptTempNewTheme(tempThemeId);
        return "redirect:/loadAllTempNewThemes";
    }


    @PostMapping("/refuseNewTheme")
    public String refuseNewTheme(HttpServletRequest request) {
        int tempThemeId = Integer.parseInt(request.getParameter("tempThemeId"));
        tempNewThemeService.refuseTempNewTheme(tempThemeId);
        return "redirect:/loadAllTempNewThemes";
    }

}
