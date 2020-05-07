import WebUtil.WebUtil;
import org.testApp.*;
import org.testApp.api.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserInSessionServlet", urlPatterns = "/userInSession")
public class UserInSessionServlet extends HttpServlet {

    private UserService userService;

    public void init() {
        userService = UserServiceImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        User authUser = userService.getUserByLogin(login);
        session.setAttribute("authUser", authUser);
        WebUtil.forword("facade", request, response);

    }
}



