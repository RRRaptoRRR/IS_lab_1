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

        // ---------- 1) Читаем данные формы в переменные ----------
        String name = request.getParameter("name");

        String coordXStr = request.getParameter("coordX");
        String coordYStr = request.getParameter("coordY");

        String genreStr = request.getParameter("genre");
        MusicGenre genre = (genreStr == null || genreStr.isEmpty()) ? null : MusicGenre.valueOf(genreStr);

        String numberOfParticipantsStr = request.getParameter("numberOfParticipants");
        String singlesCountStr = request.getParameter("singlesCount");
        String description = request.getParameter("description");

        String albumName = request.getParameter("albumName");
        String albumSalesStr = request.getParameter("albumSales");

        String albumsCountStr = request.getParameter("albumsCount");
        String establishmentDateStr = request.getParameter("establishmentDate");

        String frontManName = request.getParameter("frontManName");
        String eyeColorStr = request.getParameter("eyeColor");
        String hairColorStr = request.getParameter("hairColor");

        String locXStr = request.getParameter("locX");
        String locYStr = request.getParameter("locY");
        String locName = request.getParameter("locName");

        String birthdayStr = request.getParameter("birthday");
        String heightStr = request.getParameter("height");

        String nationalityStr = request.getParameter("nationality");
        Country nationality = (nationalityStr == null || nationalityStr.isEmpty()) ? null : Country.valueOf(nationalityStr);


        // ---------- 2) Преобразуем строки в нужные типы ----------
        float coordX = Float.parseFloat(coordXStr);
        float coordY = Float.parseFloat(coordYStr);

        int numberOfParticipants = Integer.parseInt(numberOfParticipantsStr);
        long singlesCount = Long.parseLong(singlesCountStr);

        Double albumSales = (albumSalesStr == null || albumSalesStr.isEmpty()) ? null : Double.parseDouble(albumSalesStr);

        long albumsCount = Long.parseLong(albumsCountStr);
        LocalDate establishmentDate = LocalDate.parse(establishmentDateStr);

        Color eyeColor = Color.valueOf(eyeColorStr);
        Color hairColor = Color.valueOf(hairColorStr);

        Long locX = Long.parseLong(locXStr);
        double locY = Double.parseDouble(locYStr);

        LocalDate birthday = LocalDate.parse(birthdayStr);
        Float height = (heightStr == null || heightStr.isEmpty()) ? null : Float.parseFloat(heightStr);


        // ---------- 3) Собираем объект MusicBand из переменных ----------
        MusicBand musicBand = new MusicBand(
                (long) resultsBean.getResult().size(),
                name,
                new Coordinates(coordX, coordY),
                LocalDate.now(),
                genre,
                numberOfParticipants,
                singlesCount,
                description,
                (albumName == null || albumName.trim().isEmpty()) ? null : new Album(albumName, albumSales),
                albumsCount,
                establishmentDate,
                new Person(
                        frontManName,
                        eyeColor,
                        hairColor,
                        new Location(locX, locY, locName),
                        birthday,
                        height,
                        nationality
                )
        );

        // ---------- 4) Добавляем в таблицу и возвращаем на главную ----------
        resultsBean.addMusicBandToResult(musicBand);
        response.sendRedirect(request.getContextPath() + "/controller");
    }


}
