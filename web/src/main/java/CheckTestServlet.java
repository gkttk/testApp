import WebUtil.WebUtil;
import org.testApp.Question;
import org.testApp.QuestionService;
import org.testApp.Questionnaire;
import org.testApp.api.IQuestionService;
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

    private IQuestionService questionService;

    public void init() {
        questionService = QuestionService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Questionnaire questionnaire = (Questionnaire) session.getAttribute("questionnaire");
        List<String> answers;
        int count = 0;
        for (int i = 1; i <= questionnaire.getQuestions().size(); i++) {
            if(request.getParameterValues("question_" + i + "[]") == null){
                continue;
            }
            answers = Arrays.asList(request.getParameterValues("question_" + i + "[]"));

            Question question = questionnaire.getQuestions().get(i - 1);
            count += questionService.checkQuestion(question, answers);
        }
            Double result = (double)(100/questionnaire.getQuestions().size())*count;
        session.setAttribute("result", result);
        session.setAttribute("date", new Date());

        WebUtil.forword("addQuestionnaire", request, response);
    }
}



