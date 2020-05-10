import WebUtil.WebUtil;
import org.testApp.UserValidator;
import org.testApp.api.Validator;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CheckLoginAuthServlet", urlPatterns = "/checkLoginAuth")
public class CheckLoginAuthServlet extends HttpServlet {
    private Validator userValidator;

    public void init() {
        userValidator = UserValidator.getInstance();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String userLogin = request.getParameter("login");

        if (userValidator.checkLoginInDB(userLogin)) {
            WebUtil.forword("validationPassword", request, response);
        } else {
            request.setAttribute("UserNotExistsMessage", "Такой пользователь не зарегистрирован");
            WebUtil.forword("index.jsp", request, response);
        }
    }



}

