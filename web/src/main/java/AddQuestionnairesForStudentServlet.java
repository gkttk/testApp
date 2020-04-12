import WebUtil.WebUtil;
import org.testApp.*;
import org.testApp.api.IQuestionnaireService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "AddQuestionnairesForStudentServlet", urlPatterns = "/addQForStudent")
public class AddQuestionnairesForStudentServlet extends HttpServlet {

    private IQuestionnaireService questionnaireService;

    public void init() {
        questionnaireService = QuestionnaireService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User authUser = (User)session.getAttribute("authUser");
        int authUserId = authUser.getId();
        List<Questionnaire> questionnaires = questionnaireService.getQuestionnairesForStudent(authUserId);

        session.setAttribute("studentQuestionnairesList", questionnaires);

        WebUtil.forword("getThemeNames", request, response);

    }
}



