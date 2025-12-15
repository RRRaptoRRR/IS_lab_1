package Controllers;

import Beans.ResultsBean;
import Data.*;
import DataBase.DatabaseHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
@WebServlet("/deleteBand")
public class DeleteBandController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        ResultsBean resultsBean = (ResultsBean) session.getAttribute("table");

        long id = Long.parseLong(request.getParameter("id"));

        //resultsBean.getResult().removeIf(b -> b.getId() == id);
        try (Connection connection = DatabaseHandler.getConnection()) {
            String sql = "DELETE FROM music_bands WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/controller");
    }
}
