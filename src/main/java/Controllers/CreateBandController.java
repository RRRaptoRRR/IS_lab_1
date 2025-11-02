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


@WebServlet(name = "CreateBandServlet", urlPatterns = "/createBand")

public class CreateBandController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        ResultsBean resultsBean = (ResultsBean) session.getAttribute("table");

        if (resultsBean == null) {
            resultsBean = new ResultsBean();
            session.setAttribute("table", resultsBean);
        }

        MusicBand musicBand = new MusicBand(
                (long) resultsBean.getResult().size(), // временный id
                request.getParameter("name"),
                new Coordinates(
                        Float.parseFloat(request.getParameter("coordX")),
                        Float.parseFloat(request.getParameter("coordY"))
                ),
                LocalDate.now(), // creationDate генерируется автоматически
                MusicGenre.valueOf(request.getParameter("genre")),
                Integer.parseInt(request.getParameter("numberOfParticipants")),
                Long.parseLong(request.getParameter("singlesCount")),
                request.getParameter("description"),
                new Album(
                        request.getParameter("albumName"),
                        Double.parseDouble(request.getParameter("albumSales"))
                ),
                Long.parseLong(request.getParameter("albumsCount")),
                LocalDate.parse(request.getParameter("establishmentDate")),
                new Person(
                        request.getParameter("frontManName"),
                        Color.valueOf(request.getParameter("eyeColor")),
                        Color.valueOf(request.getParameter("hairColor")),
                        new Location(
                                Long.parseLong(request.getParameter("locX")),
                                Double.parseDouble(request.getParameter("locY")),
                                request.getParameter("locName")
                        ),
                        LocalDate.parse(request.getParameter("birthday")),
                        Float.parseFloat(request.getParameter("height")),
                        Country.valueOf(request.getParameter("nationality"))
                )
        );

        resultsBean.addMusicBandToResult(musicBand);

        response.sendRedirect(request.getContextPath() + "/controller");
    }

}
