package recordstore.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.data.Artist;
import recordstore.data.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "LoginServlet", urlPatterns = "/")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("user", new User(req.getParameter("name")));
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        req.getRequestDispatcher("records.jsp").forward(req, resp);
    }

}
