import WebUtil.WebUtil;
import org.testApp.*;
import org.testApp.api.QuestionnaireService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "GetQuestionnaireServlet", urlPatterns = "/getQuestionnaire")
public class GetQuestionnaireServlet extends HttpServlet {
    private QuestionnaireService questionnaireService;

    public void init() {
        questionnaireService = QuestionnaireServiceImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String theme = request.getParameter("testTheme");
        int theme_id = Integer.parseInt(theme);
        User authUser = (User)session.getAttribute("authUser");
        int student_id = authUser.getId();

        Questionnaire questionnaire = questionnaireService.generateQuestionnaire(student_id, theme_id);

        session.setAttribute("questionnaire", questionnaire);

        WebUtil.forword("testPage.jsp", request, response);

    }
}



