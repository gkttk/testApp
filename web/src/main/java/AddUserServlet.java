import WebUtil.WebUtil;
import org.testApp.User;
import org.testApp.UserServiceImpl;
import org.testApp.api.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddUserServlet", urlPatterns = "/addUser")
public class AddUserServlet extends HttpServlet {
    private UserService userService;

    public void init() {
        userService = UserServiceImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        userService.addUser(new User(login, password, email));

        WebUtil.forword("userInSession", request, response);


    }
}
