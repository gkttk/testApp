import WebUtil.WebUtil;
import org.testApp.User;
import org.testApp.UserDetails;
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
        User user = new User(login, password, email);
        UserDetails userDetails = new UserDetails(null, null, null, null, user);


        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String age = request.getParameter("age");
        if(!name.equals("")){
            userDetails.setName(name);
        }
        if(!surname.equals("")){
            userDetails.setSurname(surname);
        }
        if(!age.equals("")){
            userDetails.setAge(Integer.parseInt(age));
        }

        user.setuDetails(userDetails);
        userService.addUser(user);

        WebUtil.forword("userInSession", request, response);


    }
}
