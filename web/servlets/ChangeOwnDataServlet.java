import WebUtil.WebUtil;
import org.testApp.User;
import org.testApp.UserServiceImpl;
import org.testApp.api.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ChangeOwnDataServlet", urlPatterns = "/changeOwnData")
public class ChangeOwnDataServlet extends HttpServlet {

    private UserService userService;

    public void init() {
        userService = UserServiceImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String newPassword = request.getParameter("newPassword");
        String newEmail = request.getParameter("newEmail");
        String newName = request.getParameter("newName");
        String newSurname = request.getParameter("newSurname");
        String newAge = request.getParameter("newAge");
        HttpSession session = request.getSession();
        User oldAuthUser = (User) session.getAttribute("authUser");
        if (!newPassword.equals("")) {
            userService.changePassword(newPassword, oldAuthUser);
        }
        if (!newEmail.equals("")) {
            userService.changeEmail(newEmail, oldAuthUser);
        }
        String userLogin = oldAuthUser.getLogin();
        User authUser = userService.getUserByLogin(userLogin);
        if(!newName.equals("")){
            authUser.getuDetails().setName(newName);
        }
        if(!newSurname.equals("")){
            authUser.getuDetails().setSurname(newSurname);
        }
        if(!newAge.equals("")){
            authUser.getuDetails().setAge(Integer.parseInt(newAge));
        }
        userService.updateUser(authUser);
        session.setAttribute("authUser", authUser);
        WebUtil.forword("helloUser.jsp", request, response);


    }
}
