import WebUtil.WebUtil;
import org.testApp.User;
import org.testApp.UserService;
import org.testApp.api.IUserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ChangeOwnDataServlet", urlPatterns = "/changeOwnData")
public class ChangeOwnDataServlet extends HttpServlet {

    private IUserService userService;

    public void init() {
        userService = UserService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String newPassword = request.getParameter("newPassword");
        String newEmail = request.getParameter("newEmail");
        HttpSession session = request.getSession();
        User oldAuthUser = (User) session.getAttribute("authUser");
        if (!newPassword.equals("")) {
            userService.changePassword(newPassword, oldAuthUser);
        }
        if (!newEmail.equals("")) {
            userService.changeEmail(newEmail, oldAuthUser);
        }
        String userLogin = oldAuthUser.getLogin();
        User authUser = userService.getUser(userLogin);
        session.setAttribute("authUser", authUser);
        WebUtil.forword("helloUser.jsp", request, response);


    }
}
