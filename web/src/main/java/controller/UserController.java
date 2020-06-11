package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.testApp.*;
import org.testApp.api.*;
import org.testApp.enums.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
    private TempNewThemeService tempNewThemeService;

    private final static int MAXRESULTONPAGE = 5;


    public UserController(Validator userValidator, UserService userService,
                          QuestionnaireService questionnaireService, ThemeService themeService, InfoForTeacherService infoForTeacherService,
                          QuestionService questionService, TempNewThemeService tempNewThemeService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.questionnaireService = questionnaireService;
        this.themeService = themeService;
        this.infoForTeacherService = infoForTeacherService;
        this.questionService = questionService;
        this.tempNewThemeService = tempNewThemeService;
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
        return "forward:/facade/"; //redirect??
    }

    @GetMapping("/facade")
    public String facade(HttpSession session) {
        User authUser = (User) session.getAttribute("authUser");
        if (authUser.getRole().equals(Role.STUDENT)) {
            return "forward:/addQForStudent/";
        } else if (authUser.getRole().equals(Role.TEACHER)) {
            return "forward:/getResultForTeacher/";
        }
        return "forward:/loadUsers/";//redirect??
    }

    @GetMapping("/addQForStudent")
    public String addQuestionnairesForStudent(HttpSession session) {
        User authUser = (User) session.getAttribute("authUser");
        int authUserId = authUser.getId();
        List<Questionnaire> questionnaires = questionnaireService.getQuestionnairesForStudent(authUserId);
        session.setAttribute("studentQuestionnairesList", questionnaires);
        return "forward:/getThemeNames/"; //redirect??

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
        return "forward:/helloUser.jsp"; //redirect??
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

        return "forward:/loadTempNewThemesForUser/";
    }

    @GetMapping("/loadUsers")
    public String loadUsers(HttpSession session) {
        List<User> users = userService.getUsersList();
        session.setAttribute("usersList", users);
        return "forward:/loadAllTempNewThemes/";
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
        if (userValidator.checkLoginInDB(login)) {
            User deleteUser = userService.getUserByLogin(login);
            int id = deleteUser.getId();
            questionnaireService.deleteQuestionnaire(id);
            userService.deleteUser(login);
            request.setAttribute("deleteUserMessage", "Пользователь успешно удален");
            return "forward:/loadUsers/";
        } else {
            request.setAttribute("deleteUserMessage", "Такого пользователя не существует");
            return "forward:/helloUser.jsp";
        }
    }

    @GetMapping("/exit")
    public String invalidateSession(HttpSession session) {
        session.invalidate();
        return "redirect:/index.jsp";
    }


    @GetMapping("/addThemeForPermit")
    public String addThemeForPermit(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String themeName = request.getParameter("themeName");
        int numberOfQuestions = Integer.parseInt(request.getParameter("numberOfQuestions"));
        User authUser = (User) session.getAttribute("authUser");
        Integer ownerId = authUser.getId();
        tempNewThemeService.addTempNewThemeForPermit(ownerId, themeName, numberOfQuestions);
        return "forward:/loadTempNewThemesForUser/";
    }

    @GetMapping("/loadTempNewThemesForUser")
    public String loadTempNewThemesForUser(HttpSession session) {
        User authUser = (User) session.getAttribute("authUser");
        List<TempNewTheme> tempNewThemes = tempNewThemeService.getTempNewThemesForUser(authUser.getId());
        session.setAttribute("tempUserNewThemes", tempNewThemes);
        return "forward:/addQForStudent/";
    }

    @GetMapping("/loadAllTempNewThemes")
    public String loadAllTempNewThemes(HttpSession session) {
        List<TempNewTheme> tempNewThemes = tempNewThemeService.getAllTempNewThemes();
        session.setAttribute("tempNewThemes", tempNewThemes);
        return "forward:/addQForStudent/";
    }

    @GetMapping("/acceptNewTheme")
    public String acceptNewTheme(HttpServletRequest request) {
        int tempThemeId = Integer.parseInt(request.getParameter("tempThemeId"));
        tempNewThemeService.acceptTempNewTheme(tempThemeId);
        return "forward:/loadAllTempNewThemes/";
    }


    @GetMapping("/refuseNewTheme")
    public String refuseNewTheme(HttpServletRequest request) {
        int tempThemeId = Integer.parseInt(request.getParameter("tempThemeId"));
        tempNewThemeService.refuseTempNewTheme(tempThemeId);
        return "forward:/loadAllTempNewThemes/";
    }

    @GetMapping("/getTempNewTheme")
    public String getTempNewTheme(HttpServletRequest request, HttpSession session) {
        int tempThemeId = Integer.parseInt(request.getParameter("newTempUserThemeId"));
        TempNewTheme tempNewTheme = tempNewThemeService.getTempNewTheme(tempThemeId);
        session.setAttribute("newTempUserTheme", tempNewTheme);

        return "forward:/describeNewTheme.jsp";
    }

   @GetMapping("/addNewTheme")
    public String addNewTheme(HttpServletRequest request, HttpSession session) {

       User authUser = (User) session.getAttribute("authUser");
       int ownerId = authUser.getId();   //id Owner

       String newThemeName = request.getParameter("newTempThemeName"); //theme name
        List<Question> questions = new ArrayList<>();

        Theme theme = new Theme(null, newThemeName, ownerId);


       String[] questionTexts = request.getParameterValues("questionText");


        for (int i = 0; i < questionTexts.length; i++) {   //тема: описание + владелецИД
           if (questionTexts[i].equals("")){
               break;
           }
            String questionText = questionTexts[i];//вопрос: описание + лист ответов + тема
            Question question = new Question(null, questionText, new ArrayList<>(),theme);

            String[] answerTexts = request.getParameterValues("answer" + (i+1)); //ответ: описание + правильность + вопрос
            for (int j = 0; j <answerTexts.length ; j++) {
                if (answerTexts[j].equals("")){
                    break;
                }
                String answerText = answerTexts[j];
                String correctnessStr = request.getParameter("answerCorrectness" + (i + 1) + "_" + (j + 1));
                Answer answer = new Answer(null, answerText, "false", question);
                if (correctnessStr != null){
                   answer.setCorrectness("true");
                }
                question.getAnswers().add(answer);

            }
            questions.add(question);
        }

        theme.settQuestions(questions);
        themeService.addNewTheme(theme);

       TempNewTheme newTempUserTheme = (TempNewTheme)session.getAttribute("newTempUserTheme");
       tempNewThemeService.refuseTempNewTheme(newTempUserTheme.getId());
       session.removeAttribute("newTempUserTheme");


       return "forward:/loadAllTempNewThemes/";
}




}
