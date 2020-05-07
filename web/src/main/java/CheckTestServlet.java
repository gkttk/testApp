import WebUtil.WebUtil;
import org.testApp.Question;
import org.testApp.QuestionServiceImpl;
import org.testApp.Questionnaire;
import org.testApp.api.QuestionService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CheckTestServlet", urlPatterns = "/checkTest")
public class CheckTestServlet extends HttpServlet {

    private QuestionService questionService;

    public void init() {
        questionService = QuestionServiceImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Questionnaire questionnaire = (Questionnaire) session.getAttribute("questionnaire");
        List<String> answers;
        int count = 0;
        for (int i = 1; i <= questionnaire.getQuestionnaireQuestions().size(); i++) {
            if(request.getParameterValues("question_" + i + "[]") == null){
                continue;
            }
            answers = Arrays.asList(request.getParameterValues("question_" + i + "[]"));

            Question question = questionnaire.getQuestionnaireQuestions().get(i - 1);
            count += questionService.checkQuestion(question, answers);
        }
            Double result = (double)(100/questionnaire.getQuestionnaireQuestions().size())*count;
        session.setAttribute("result", result);
        session.setAttribute("date", new Date());

        WebUtil.forword("addQuestionnaire", request, response);
    }
}



