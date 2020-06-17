package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.testApp.Question;
import org.testApp.Questionnaire;
import org.testApp.User;
import org.testApp.api.QuestionService;
import org.testApp.api.QuestionnaireService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class TestController {

    private QuestionnaireService questionnaireService;
    private QuestionService questionService;

    public TestController(QuestionnaireService questionnaireService, QuestionService questionService) {
        this.questionnaireService = questionnaireService;
        this.questionService = questionService;
    }

    @PostMapping("/getQuestionnaire")
    public String getQuestionnaire(HttpServletRequest request, HttpSession session) {

        String theme = request.getParameter("testTheme");
        int themeId = Integer.parseInt(theme);
        User authUser = (User) session.getAttribute("authUser");
        int studentId = authUser.getId();
        Questionnaire questionnaire = questionnaireService.generateQuestionnaire(studentId, themeId);
        session.setAttribute("questionnaire", questionnaire);
        return "testPage";
    }

    @PostMapping("/checkTest")
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

        return "redirect:/addQuestionnaire/";
    }

    @GetMapping("/addQuestionnaire")
    public String addQuestionnaireInDb(HttpSession session) {
        Double result = (Double) session.getAttribute("result");
        Questionnaire questionnaire = (Questionnaire) session.getAttribute("questionnaire");
        questionnaireService.addQuestionnaireInDb(questionnaire, result);
        return "testResultPage";
    }


}
