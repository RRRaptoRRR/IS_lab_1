package Controllers;

import Beans.ResultsBean;
import Data.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "/controller", urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        ResultsBean tempBean = new ResultsBean();
        ArrayList<MusicBand> bands = tempBean.getResult();

        // 2. Кладем СПИСОК в request (для table-rows.jsp, который внутри main.jsp)
        request.setAttribute("bandsList", bands);

        // 3. Открываем главную страницу
        // main.jsp включит в себя table-rows.jsp, который найдет "bandsList" и отрисует его.
        getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
    }
}