import WebUtil.WebUtil;
import org.testApp.QuestionnaireService;
import org.testApp.User;
import org.testApp.UserService;
import org.testApp.UserValidator;
import org.testApp.api.IQuestionService;
import org.testApp.api.IQuestionnaireService;
import org.testApp.api.IUserService;
import org.testApp.api.IValidator;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteUserServlet", urlPatterns = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private IUserService userService;
    private IValidator userValidator;
    private IQuestionnaireService questionnaireService;

    public void init() {
        userService = UserService.getInstance();
        userValidator = UserValidator.getInstance();
        questionnaireService = QuestionnaireService.getInstance();
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
