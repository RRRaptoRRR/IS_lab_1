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

@WebServlet(name = "EditBandServlet", urlPatterns = "/editBand")
public class EditBandController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        HttpSession session = request.getSession();
        ResultsBean resultsBean = (ResultsBean) session.getAttribute("table");

        long id = Long.parseLong(request.getParameter("id"));

        MusicBand band = resultsBean.getResult().stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);

        request.setAttribute("band", band); // передаём в форму
        request.getRequestDispatcher("/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        HttpSession session = request.getSession();
        ResultsBean resultsBean = (ResultsBean) session.getAttribute("table");

        long id = Long.parseLong(request.getParameter("id"));

        for (MusicBand band : resultsBean.getResult()) {
            if (band.getId() == id) {

                // ---- читаем параметры и обновляем поля ----
                band.setName(request.getParameter("name"));
                band.setCoordinates(new Coordinates(
                        Float.parseFloat(request.getParameter("coordX")),
                        Float.parseFloat(request.getParameter("coordY"))
                ));
                String genreStr = request.getParameter("genre");
                band.setGenre(genreStr.isEmpty() ? null : MusicGenre.valueOf(genreStr));
                band.setNumberOfParticipants(Integer.parseInt(request.getParameter("numberOfParticipants")));
                band.setSinglesCount(Long.parseLong(request.getParameter("singlesCount")));
                band.setDescription(request.getParameter("description"));

                String albumName = request.getParameter("albumName");
                String albumSalesStr = request.getParameter("albumSales");
                band.setBestAlbum(albumName.isEmpty() ? null : new Album(albumName,
                        albumSalesStr.isEmpty() ? null : Double.parseDouble(albumSalesStr)));

                band.setAlbumsCount(Long.parseLong(request.getParameter("albumsCount")));
                band.setEstablishmentDate(LocalDate.parse(request.getParameter("establishmentDate")));

                band.setFrontMan(new Person(
                        request.getParameter("frontManName"),
                        Color.valueOf(request.getParameter("eyeColor")),
                        Color.valueOf(request.getParameter("hairColor")),
                        new Location(
                                Long.parseLong(request.getParameter("locX")),
                                Double.parseDouble(request.getParameter("locY")),
                                request.getParameter("locName")),
                        LocalDate.parse(request.getParameter("birthday")),
                        request.getParameter("height").isEmpty() ? null : Float.parseFloat(request.getParameter("height")),
                        request.getParameter("nationality").isEmpty() ? null : Country.valueOf(request.getParameter("nationality"))
                ));

                break;
            }
        }

        response.sendRedirect(request.getContextPath() + "/controller");
    }
}

