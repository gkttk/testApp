import WebUtil.WebUtil;
import org.testApp.Questionnaire;
import org.testApp.QuestionnaireDaoImpl;
import org.testApp.api.QuestionnaireDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddQuestionnaireServlet", urlPatterns = "/addQuestionnaire")
public class AddQuestionnaireServlet extends HttpServlet {
    private QuestionnaireDao questionnaireDao;

    public void init() {
        questionnaireDao = QuestionnaireDaoImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Double result = (Double) session.getAttribute("result");
        Questionnaire questionnaire = (Questionnaire) session.getAttribute("questionnaire");
        questionnaireDao.add(questionnaire, result);
        WebUtil.forword("testResultPage.jsp", request, response);


    }
}
