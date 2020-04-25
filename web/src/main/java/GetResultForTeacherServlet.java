import WebUtil.WebUtil;
import org.testApp.InfoForTeacher;
import org.testApp.InfoForTeacherServiceImpl;
import org.testApp.api.InfoForTeacherService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "GetResultForTeacherServlet", urlPatterns = "/getResultForTeacher")
public class GetResultForTeacherServlet extends HttpServlet {
    private InfoForTeacherService infoForTeacherService;

    public void init() {
        infoForTeacherService = InfoForTeacherServiceImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<InfoForTeacher> infoForTeacherList = infoForTeacherService.getResults();
        HttpSession session = request.getSession();
        session.setAttribute("infoForTeacher", infoForTeacherList);
        WebUtil.forword("addQForStudent", request, response);

    }
}
