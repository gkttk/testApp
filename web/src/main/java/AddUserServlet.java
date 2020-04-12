import WebUtil.WebUtil;
import org.testApp.User;
import org.testApp.UserService;
import org.testApp.api.IUserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddUserServlet", urlPatterns = "/addUser")
public class AddUserServlet extends HttpServlet {
    private IUserService userService;

    public void init() {
        userService = UserService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        userService.addUser(new User(login, password, email));

        WebUtil.forword("userInSession", request, response);


    }
}
