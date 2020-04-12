import WebUtil.WebUtil;
import org.testApp.User;
import org.testApp.UserService;
import org.testApp.api.IUserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "LoadUsersServlet", urlPatterns = "/loadUsers")
public class LoadUsersServlet extends HttpServlet {
    private IUserService userService;

    public void init() {
        userService = UserService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userService.getUsersList();
        HttpSession session = request.getSession();
        session.setAttribute("usersList", users);
        WebUtil.forword("addQForStudent", request, response);

    }
}
