package filters;

import org.testApp.User;
import org.testApp.UserServiceImpl;
import org.testApp.api.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter //(urlPatterns = "/facade")
public class UserInSessionFilter implements Filter {

    private UserService userService;

    public void init(FilterConfig config) {
        userService = UserServiceImpl.getInstance();
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        User authUser = userService.getUserByLogin(login);  //запрос в БД через сервис, по логам запрос проходит корректно
        session.setAttribute("authUser", authUser);
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

}
