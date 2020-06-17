package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.testApp.*;
import org.testApp.api.InfoForTeacherService;
import org.testApp.api.QuestionnaireService;
import org.testApp.api.TempNewThemeService;
import org.testApp.api.ThemeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class TeacherController {

    private InfoForTeacherService infoForTeacherService;
    private QuestionnaireService questionnaireService;
    private TempNewThemeService tempNewThemeService;
    private ThemeService themeService;

    public TeacherController(InfoForTeacherService infoForTeacherService, QuestionnaireService questionnaireService,
                             TempNewThemeService tempNewThemeService, ThemeService themeService) {
        this.infoForTeacherService = infoForTeacherService;
        this.questionnaireService = questionnaireService;
        this.tempNewThemeService = tempNewThemeService;
        this.themeService = themeService;
    }

    private final static int MAXRESULTONPAGE = 5;

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

        return "redirect:/loadTempNewThemesForUser/";
    }

    @GetMapping("/loadTempNewThemesForUser")
    public String loadTempNewThemesForUser(HttpSession session) {
        User authUser = (User) session.getAttribute("authUser");
        List<TempNewTheme> tempNewThemes = tempNewThemeService.getTempNewThemesForUser(authUser.getId());
        session.setAttribute("tempUserNewThemes", tempNewThemes);
        return "redirect:/addQForStudent/";
    }



    @PostMapping("/addThemeForPermit")
    public String addThemeForPermit(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String themeName = request.getParameter("themeName");
        int numberOfQuestions = Integer.parseInt(request.getParameter("numberOfQuestions"));
        User authUser = (User) session.getAttribute("authUser");
        Integer ownerId = authUser.getId();
        tempNewThemeService.addTempNewThemeForPermit(ownerId, themeName, numberOfQuestions);
        return "redirect:/loadTempNewThemesForUser/";
    }



    @GetMapping("/getTempNewTheme")
    public String getTempNewTheme(HttpServletRequest request, HttpSession session) {
        int tempThemeId = Integer.parseInt(request.getParameter("newTempUserThemeId"));
        TempNewTheme tempNewTheme = tempNewThemeService.getTempNewTheme(tempThemeId);
        session.setAttribute("newTempUserTheme", tempNewTheme);

        return "describeNewThemePage";
    }

    @PostMapping("/addNewTheme")
    public String addNewTheme(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        User authUser = (User) session.getAttribute("authUser");
        int ownerId = authUser.getId();   //id Owner

        String newThemeName = request.getParameter("newTempThemeName"); //theme name /1
        List<Question> questions = new ArrayList<>();

        Theme theme = new Theme(null, newThemeName, ownerId);


        String[] questionTexts = request.getParameterValues("questionText");  //2


        for (int i = 0; i < questionTexts.length; i++) {   //тема: описание + владелецИД
            if (questionTexts[i].isEmpty()) {
                break;
            }
            String questionText = questionTexts[i];//вопрос: описание + лист ответов + тема
            Question question = new Question(null, questionText, new ArrayList<>(), theme);

            String[] answerTexts = request.getParameterValues("answer" + (i + 1)); //ответ: описание + правильность + вопрос
            for (int j = 0; j < answerTexts.length; j++) {
                if (answerTexts[j].isEmpty()) {
                    break;
                }
                String answerText = answerTexts[j];
                String correctnessStr = request.getParameter("answerCorrectness" + (i + 1) + "_" + (j + 1));
                Answer answer = new Answer(null, answerText, "false", question);
                if (correctnessStr != null) {
                    answer.setCorrectness("true");
                }
                question.getAnswers().add(answer);

            }
            questions.add(question);
        }

        theme.settQuestions(questions);
        themeService.addNewTheme(theme);

        TempNewTheme newTempUserTheme = (TempNewTheme) session.getAttribute("newTempUserTheme");
        tempNewThemeService.refuseTempNewTheme(newTempUserTheme.getId());
        session.removeAttribute("newTempUserTheme");

        return "redirect:/refreshThemes/";
    }

    @GetMapping("/refreshThemes")
    public String refreshThemes(HttpSession session){
        List<Theme> themes = themeService.getAllThemes();
        session.setAttribute("allThemes", themes);
        return "redirect:/loadTempNewThemesForUser/";
    }

    @GetMapping("/redirectAddThemePage")
    public String redirectAddThemePage(){
        return "addThemePage";
    }

}