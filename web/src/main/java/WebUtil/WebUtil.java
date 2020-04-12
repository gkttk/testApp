package WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtil {

    public static void forword(String page, HttpServletRequest rq, HttpServletResponse rs) {
        try {
            rq.getRequestDispatcher("/" + page).forward(rq, rs);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
