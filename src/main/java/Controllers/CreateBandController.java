package Controllers;

import Beans.ResultsBean;
import Data.MusicBand.MusicBand;
import Data.MusicBand.MusicGenre;
import Data.MusicBand.Person;
import DataBase.DatabaseHandler;

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


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
// ... (ваш код сбора переменных) ...

// ВМЕСТО resultsBean.addMusicBandToResult(musicBand); пишем:

        try (java.sql.Connection connection = DatabaseHandler.getConnection()) {
            String sql = "INSERT INTO music_bands (name, coord_x, coord_y, genre, number_of_participants, singles_count, description, " +
                    "best_album_name, best_album_sales, albums_count, establishment_date, " +
                    "frontman_name, frontman_eye_color, frontman_hair_color, " +
                    "frontman_loc_x, frontman_loc_y, frontman_loc_name, " +
                    "frontman_birthday, frontman_height, frontman_nationality) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            java.sql.PreparedStatement statement = connection.prepareStatement(sql);

            // Заполняем параметры
            statement.setString(1, name);
            statement.setFloat(2, coordX);
            statement.setFloat(3, coordY);
            statement.setString(4, genre != null ? genre.name() : null);
            statement.setInt(5, numberOfParticipants);
            statement.setLong(6, singlesCount);
            statement.setString(7, description);

            // Альбом (проверка на null)
            statement.setString(8, albumName != null && !albumName.isEmpty() ? albumName : null);
            if (albumSales != null) statement.setDouble(9, albumSales); else statement.setNull(9, java.sql.Types.DOUBLE);

            statement.setLong(10, albumsCount);
            statement.setObject(11, establishmentDate); // LocalDate SQL умеет конвертировать

            // FrontMan
            statement.setString(12, frontManName);
            statement.setString(13, eyeColor.name());
            statement.setString(14, hairColor.name());
            statement.setLong(15, locX);
            statement.setDouble(16, locY);
            statement.setString(17, locName);
            statement.setObject(18, birthday);
            if (height != null) statement.setFloat(19, height); else statement.setNull(19, java.sql.Types.FLOAT);
            statement.setString(20, nationality != null ? nationality.name() : null);

            statement.executeUpdate();

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            // Можно добавить вывод ошибки пользователю
            throw new ServletException("Ошибка БД", e);
        }

// Перенаправление остаётся тем же
        response.sendRedirect(request.getContextPath() + "/controller");
    }


}
