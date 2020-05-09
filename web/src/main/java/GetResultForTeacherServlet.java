import WebUtil.WebUtil;
import org.testApp.InfoForTeacher;
import org.testApp.InfoForTeacherServiceImpl;
import org.testApp.QuestionnaireDaoImpl;
import org.testApp.api.InfoForTeacherService;
import org.testApp.api.QuestionnaireDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "GetResultForTeacherServlet", urlPatterns = "/getResultForTeacher")
public class GetResultForTeacherServlet extends HttpServlet {
    private InfoForTeacherService infoForTeacherService;
    private QuestionnaireDao questionnaireDao;


    private static int maxResultsOnPage = 5;
    public void init() {
        infoForTeacherService = InfoForTeacherServiceImpl.getInstance();
        questionnaireDao = QuestionnaireDaoImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int currentPage = 1;
        HttpSession session = request.getSession();

        if(request.getParameter("currentPage") != null){
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        List<InfoForTeacher> infoForTeacherList = infoForTeacherService.getResultsPagination(currentPage, maxResultsOnPage);
        int resultsCount = questionnaireDao.countOfQuestionnaires().intValue();
        int pagesCount = (int)Math.ceil((resultsCount * 1.0) / maxResultsOnPage );

        session.setAttribute("infoForTeacher", infoForTeacherList);
        session.setAttribute("pagesCount", pagesCount);
        session.setAttribute("currentPage", currentPage);




        WebUtil.forword("addQForStudent", request, response);

    }
}
