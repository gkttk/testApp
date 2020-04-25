import WebUtil.WebUtil;
import org.testApp.QuestionnaireServiceImpl;
import org.testApp.User;
import org.testApp.UserServiceImpl;
import org.testApp.UserValidator;
import org.testApp.api.QuestionnaireService;
import org.testApp.api.UserService;
import org.testApp.api.Validator;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteUserServlet", urlPatterns = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private UserService userService;
    private Validator userValidator;
    private QuestionnaireService questionnaireService;

    public void init() {
        userService = UserServiceImpl.getInstance();
        userValidator = UserValidator.getInstance();
        questionnaireService = QuestionnaireServiceImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("deleteUserLogin");
        if(userValidator.checkLoginInDB(login)){
            User deleteUser = userService.getUser(login);
            int id = deleteUser.getId();
            questionnaireService.deleteQuestionnaire(id);
            userService.deleteUser(login);
            request.setAttribute("deleteUserMessage", "Пользователь успешно удален");
            WebUtil.forword("loadUsers", request, response);
        }
        else{
            request.setAttribute("deleteUserMessage", "Такого пользователя не существует");
            WebUtil.forword("helloUser.jsp", request, response);
        }


    }
}
