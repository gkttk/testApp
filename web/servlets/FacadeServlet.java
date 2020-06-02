import WebUtil.WebUtil;
import org.testApp.User;
import org.testApp.enums.Role;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "FacadeServlet", urlPatterns = "/facade")
public class FacadeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    User authUser = (User)session.getAttribute("authUser");
        if(authUser.getRole().equals(Role.STUDENT)){
            WebUtil.forword("addQForStudent", request, response);
        }
        else if(authUser.getRole().equals(Role.TEACHER)){
            WebUtil.forword("getResultForTeacher", request, response);
        }
        else{
            WebUtil.forword("loadUsers", request, response);
        }
    }
}
