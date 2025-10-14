import Beans.TestBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("name", "Tom");
        TestBean testBean = new TestBean();
        testBean.setName("tester");
        testBean.setAge(0);
        session.setAttribute("testBean", testBean);
        getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
    }
}