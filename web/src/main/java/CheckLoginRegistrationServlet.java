import WebUtil.WebUtil;
import org.testApp.UserValidator;
import org.testApp.api.IValidator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CheckLoginRegistrationServlet", urlPatterns = "/validationLoginReg")
public class CheckLoginRegistrationServlet extends HttpServlet {
    private IValidator userValidator;

    public void init() {
        userValidator = UserValidator.getInstance();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String userLogin = request.getParameter("login");
        if (userValidator.checkLoginInDB(userLogin)) {
            request.setAttribute("UserExistsMessage", "Логин занят");
            WebUtil.forword("registration.jsp", request, response);
        } else {
            WebUtil.forword("addUser", request, response);
        }
    }



}

