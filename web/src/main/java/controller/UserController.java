package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.testApp.*;
import org.testApp.api.*;
import org.testApp.enums.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class UserController {

    private Validator userValidator;
    private UserService userService;
    private QuestionnaireService questionnaireService;
    private ThemeService themeService;
    private InfoForTeacherService infoForTeacherService;
    private QuestionService questionService;

    private final static int MAXRESULTONPAGE = 5;


    public UserController(Validator userValidator, UserService userService,
                          QuestionnaireService questionnaireService, ThemeService themeService, InfoForTeacherService infoForTeacherService,
                          QuestionService questionService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.questionnaireService = questionnaireService;
        this.themeService = themeService;
        this.infoForTeacherService = infoForTeacherService;
        this.questionService = questionService;
    }

    @GetMapping("/validationLoginReg")
    public String checkLoginAfterRegistration(HttpServletRequest request) {
        String userLogin = request.getParameter("login");
        if (userValidator.checkLoginInDB(userLogin)) {
            request.setAttribute("UserExistsMessage", "Логин занят");
            return "forward:/registration.jsp";
        }
        return "forward:/addUser/";
    }

    @GetMapping("/addUser")
    public String addUserInDb(HttpServletRequest request) {
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        String userEmail = request.getParameter("email");
        User user = new User(userLogin, userPassword, userEmail);
        UserDetails userDetails = new UserDetails(null, null, null, null, user);

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String age = request.getParameter("age");
        if (!name.equals("")) {
            userDetails.setName(name);
        }
        if (!surname.equals("")) {
            userDetails.setSurname(surname);
        }
        if (!age.equals("")) {
            userDetails.setAge(Integer.parseInt(age));
        }

        user.setuDetails(userDetails);
        userService.addUser(user);

        return "forward:/userInSession/";
    }

    @GetMapping("/userInSession")
    public String addUserInSession(HttpServletRequest request, HttpSession session) {
        String login = request.getParameter("login");
        User authUser = userService.getUserByLogin(login);
        session.setAttribute("authUser", authUser);
        return "forward:/facade/";
    }

    @GetMapping("/facade")
    public String facade(HttpSession session) {
        User authUser = (User) session.getAttribute("authUser");
        if (authUser.getRole().equals(Role.STUDENT)) {
            return "forward:/addQForStudent/";
        } else if (authUser.getRole().equals(Role.TEACHER)) {
            return "forward:/getResultForTeacher/";
        }
        return "forward:/loadUsers/";
    }

    @GetMapping("/addQForStudent")
    public String addQuestionnairesForStudent(HttpSession session) {
        User authUser = (User) session.getAttribute("authUser");
        int authUserId = authUser.getId();
        List<Questionnaire> questionnaires = questionnaireService.getQuestionnairesForStudent(authUserId);
        session.setAttribute("studentQuestionnairesList", questionnaires);
        return "forward:/getThemeNames/";

    }

    @GetMapping("/getThemeNames")
    public String getThemeNames(HttpSession session) {
        int theme_id = 0;
        String themeName = null;
        List<Questionnaire> questionnaires = (List<Questionnaire>) session.getAttribute("studentQuestionnairesList");

        for (int i = 1; i <= questionnaires.size(); i++) {
            theme_id = questionnaires.get(i - 1).getQuestionnaireTheme().getId();
            themeName = themeService.getThemeName(theme_id);
            session.setAttribute("themeName" + i, themeName);
        }
        return "forward:/helloUser.jsp";
    }

    @GetMapping("/getResultForTeacher")
    public String getResultForTeacher(HttpServletRequest request, HttpSession session) {
        int currentPage = 1;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        List<InfoForTeacher> infoForTeacherList = infoForTeacherService.getResultsPagination(currentPage, MAXRESULTONPAGE);
        int resultsCount = questionnaireService.questionnairesCount();
        int pagesCount = (int) Math.ceil((resultsCount * 1.0) / MAXRESULTONPAGE);

        session.setAttribute("infoForTeacher", infoForTeacherList);
        session.setAttribute("pagesCount", pagesCount);
        session.setAttribute("currentPage", currentPage);

        return "forward:/addQForStudent/";
    }

    @GetMapping("/loadUsers")
    public String loadUsers(HttpSession session) {
        List<User> users = userService.getUsersList();
        session.setAttribute("usersList", users);
        return "forward:/addQForStudent/";
    }


    @GetMapping("/checkLoginAuth")
    public String checkLoginAuth(HttpServletRequest request) {
        String userLogin = request.getParameter("login");
        if (userValidator.checkLoginInDB(userLogin)) {
            return "forward:/validationPassword/";
        }
        request.setAttribute("UserNotExistsMessage", "Такой пользователь не зарегистрирован");
        return "forward:/index.jsp";
    }

    @GetMapping("/validationPassword")
    public String validationPassword(HttpServletRequest request) {
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");

        if (userValidator.checkUserPassword(userLogin, userPassword)) {
            return "forward:/userInSession/";
        }

        request.setAttribute("UserWrongPasswordMessage", "Неправильный пароль");
        return "forward:/index.jsp";

    }

    @GetMapping("/getQuestionnaire")
    public String getQuestionnaire(HttpServletRequest request, HttpSession session) {

        String theme = request.getParameter("testTheme");
        int themeId = Integer.parseInt(theme);
        User authUser = (User) session.getAttribute("authUser");
        int studentId = authUser.getId();
        Questionnaire questionnaire = questionnaireService.generateQuestionnaire(studentId, themeId);
        session.setAttribute("questionnaire", questionnaire);

        return "forward:/testPage.jsp";
    }

    @GetMapping("/checkTest")
    public String checkTest(HttpServletRequest request, HttpSession session) {
        Questionnaire questionnaire = (Questionnaire) session.getAttribute("questionnaire");
        List<String> answers;
        int count = 0;
        for (int i = 1; i <= questionnaire.getQuestionnaireQuestions().size(); i++) {
            if (request.getParameterValues("question_" + i + "[]") == null) {
                continue;
            }
            answers = Arrays.asList(request.getParameterValues("question_" + i + "[]"));

            Question question = questionnaire.getQuestionnaireQuestions().get(i - 1);
            count += questionService.checkQuestion(question, answers);
        }
        Double result = (double) (100 / questionnaire.getQuestionnaireQuestions().size()) * count;
        session.setAttribute("result", result);
        session.setAttribute("date", new Date());

        return "forward:/addQuestionnaire/";
    }

    @GetMapping("/addQuestionnaire")
    public String addQuestionnaireInDb(HttpSession session) {
        Double result = (Double) session.getAttribute("result");
        Questionnaire questionnaire = (Questionnaire) session.getAttribute("questionnaire");
        questionnaireService.addQuestionnaireInDb(questionnaire, result);
        return "forward:/testResultPage.jsp";
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

        return "forward:/helloUser.jsp";
    }

    @GetMapping("/deleteUser")
    public String deleteUserForAdmin(HttpServletRequest request) {
        String login = request.getParameter("deleteUserLogin");
        if(userValidator.checkLoginInDB(login)){
            User deleteUser = userService.getUserByLogin(login);
            int id = deleteUser.getId();
            questionnaireService.deleteQuestionnaire(id);
            userService.deleteUser(login);
            request.setAttribute("deleteUserMessage", "Пользователь успешно удален");
            return "forward:/loadUsers/";
        }
        else{
            request.setAttribute("deleteUserMessage", "Такого пользователя не существует");
            return "forward:/helloUser.jsp";
        }
    }

    @GetMapping("/exit")
    public String invalidateSession(HttpSession session){
        session.invalidate();
        return "redirect:/index.jsp";
    }

}
