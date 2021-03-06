import WebUtil.WebUtil;
import org.testApp.Questionnaire;
import org.testApp.ThemeServiceImpl;
import org.testApp.api.ThemeService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "GetThemeNamesServlet", urlPatterns = "/getThemeNames")
public class GetThemeNamesServlet extends HttpServlet {

    private ThemeService themeService;

    public void init() {
        themeService = ThemeServiceImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int theme_id = 0;
        String themeName = null;
        List<Questionnaire> questionnaires = (List<Questionnaire>) session.getAttribute("studentQuestionnairesList");

        for (int i = 1; i <= questionnaires.size(); i++) {
            theme_id = questionnaires.get(i - 1).getQuestionnaireTheme().getId();
            themeName = themeService.getThemeName(theme_id);
            session.setAttribute("themeName" + i, themeName);
        }

        WebUtil.forword("helloUser.jsp", request, response);

    }
}



