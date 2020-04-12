import WebUtil.WebUtil;
import org.testApp.UserValidator;
import org.testApp.api.IValidator;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ValidationPasswordServlet", urlPatterns = "/validationPassword")
public class ValidationPasswordServlet extends HttpServlet {
    private IValidator userValidator;

    public void init(){
            userValidator = UserValidator.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String userLogin = request.getParameter("login");
        String userPassword= request.getParameter("password");

        if(userValidator.checkUserPassword(userLogin, userPassword)){
            WebUtil.forword("userInSession",request,response);
        }
        else{
            request.setAttribute("UserWrongPasswordMessage", "Неправильный пароль");
            WebUtil.forword("index.jsp",request,response);
        }
    }

    }

