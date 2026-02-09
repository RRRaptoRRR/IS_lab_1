package Controllers;

import Beans.ResultsBean;
import Data.MusicBand.MusicBand;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/table-update")
public class TableUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Загружаем свежие данные из БД
        ResultsBean tempBean = new ResultsBean();
        ArrayList<MusicBand> bands = tempBean.getResult();

        // 2. Кладем СПИСОК в request
        request.setAttribute("bandsList", bands);

        // 3. Отдаем только фрагмент
        request.getRequestDispatcher("/table-rows.jsp").forward(request, response);
    }
}
