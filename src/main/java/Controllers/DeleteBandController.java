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
@WebServlet("/deleteBand")
public class DeleteBandController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        ResultsBean resultsBean = (ResultsBean) session.getAttribute("table");

        long id = Long.parseLong(request.getParameter("id"));

        resultsBean.getResult().removeIf(b -> b.getId() == id);

        response.sendRedirect(request.getContextPath() + "/controller");
    }
}
