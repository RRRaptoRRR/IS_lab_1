package Controllers;

import Data.*;
import DataBase.DatabaseHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

@WebServlet("/editBand")
public class EditBandController extends HttpServlet {

    //Загружает данные группы из БД и открывает form.jsp в режиме редактирования
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        // Если ID не передан, возвращаем на главную страницу
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/controller");
            return;
        }

        try {
            long id = Long.parseLong(idStr);
            MusicBand band = getBandById(id);

            if (band != null) {
                // if band != null -> форма будет заполнена данными band
                request.setAttribute("band", band);
                request.getRequestDispatcher("/form.jsp").forward(request, response);
            } else {
                // Если группа с таким ID не найдена
                response.sendRedirect(request.getContextPath() + "/controller");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/controller");
        }
    }

    //Принимает измененные данные из формы и выполняет UPDATE в БД.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // Получаем ID из скрытого поля формы
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/controller");
            return;
        }
        long id = Long.parseLong(idStr);

        // --- Чтение параметров формы ---
        String name = request.getParameter("name");

        // Координаты
        float coordX = Float.parseFloat(request.getParameter("coordX"));
        float coordY = Float.parseFloat(request.getParameter("coordY"));

        // Жанр (может быть null)
        String genreStr = request.getParameter("genre");
        MusicGenre genre = (genreStr == null || genreStr.isEmpty()) ? null : MusicGenre.valueOf(genreStr);

        // Числовые поля
        int numberOfParticipants = Integer.parseInt(request.getParameter("numberOfParticipants"));
        long singlesCount = Long.parseLong(request.getParameter("singlesCount"));
        String description = request.getParameter("description");

        // Альбом (может быть null)
        String albumName = request.getParameter("albumName");
        String albumSalesStr = request.getParameter("albumSales");
        Double albumSales = (albumSalesStr == null || albumSalesStr.isEmpty()) ? null : Double.parseDouble(albumSalesStr);

        long albumsCount = Long.parseLong(request.getParameter("albumsCount"));
        LocalDate establishmentDate = LocalDate.parse(request.getParameter("establishmentDate"));

        // Фронтмен
        String frontManName = request.getParameter("frontManName");
        Color eyeColor = Color.valueOf(request.getParameter("eyeColor"));
        Color hairColor = Color.valueOf(request.getParameter("hairColor"));
        LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));

        String heightStr = request.getParameter("height");
        Float height = (heightStr == null || heightStr.isEmpty()) ? null : Float.parseFloat(heightStr);

        String nationalityStr = request.getParameter("nationality");
        Country nationality = (nationalityStr == null || nationalityStr.isEmpty()) ? null : Country.valueOf(nationalityStr);

        // Локация фронтмена
        Long locX = Long.parseLong(request.getParameter("locX"));
        double locY = Double.parseDouble(request.getParameter("locY"));
        String locName = request.getParameter("locName");

        // SQL UPDATE
        String sql = "UPDATE music_bands SET " +
                "name=?, coord_x=?, coord_y=?, genre=?, number_of_participants=?, singles_count=?, description=?, " +
                "best_album_name=?, best_album_sales=?, albums_count=?, establishment_date=?, " +
                "frontman_name=?, frontman_eye_color=?, frontman_hair_color=?, " +
                "frontman_loc_x=?, frontman_loc_y=?, frontman_loc_name=?, " +
                "frontman_birthday=?, frontman_height=?, frontman_nationality=? " +
                "WHERE id=?";

        try (Connection connection = DatabaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setFloat(2, coordX);
            statement.setFloat(3, coordY);

            if (genre != null) statement.setString(4, genre.name());
            else statement.setNull(4, Types.VARCHAR);

            statement.setInt(5, numberOfParticipants);
            statement.setLong(6, singlesCount);
            statement.setString(7, description);

            // Альбом
            if (albumName != null && !albumName.trim().isEmpty()) {
                statement.setString(8, albumName);
                if (albumSales != null) statement.setDouble(9, albumSales);
                else statement.setNull(9, Types.DOUBLE);
            } else {
                statement.setNull(8, Types.VARCHAR);
                statement.setNull(9, Types.DOUBLE);
            }

            statement.setLong(10, albumsCount);
            statement.setObject(11, establishmentDate);

            // Фронтмен
            statement.setString(12, frontManName);
            statement.setString(13, eyeColor.name());
            statement.setString(14, hairColor.name());
            statement.setLong(15, locX);
            statement.setDouble(16, locY);
            statement.setString(17, locName);
            statement.setObject(18, birthday);

            if (height != null) statement.setFloat(19, height);
            else statement.setNull(19, Types.FLOAT);

            if (nationality != null) statement.setString(20, nationality.name());
            else statement.setNull(20, Types.VARCHAR);

            // ID для WHERE
            statement.setLong(21, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new ServletException("Ошибка при обновлении записи в БД", e);
        }

        // Возврат на главную страницу после успешного сохранения
        response.sendRedirect(request.getContextPath() + "/controller");
    }

    // Вспомогательный метод для чтения из БД
    private MusicBand getBandById(long id) {
        String sql = "SELECT * FROM music_bands WHERE id = ?";
        try (Connection connection = DatabaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // Маппинг ResultSet -> Java Object
                    Coordinates coords = new Coordinates(rs.getFloat("coord_x"), rs.getFloat("coord_y"));

                    Album album = null;
                    if (rs.getString("best_album_name") != null) {
                        album = new Album(rs.getString("best_album_name"), rs.getDouble("best_album_sales"));
                    }

                    Location location = new Location(
                            rs.getLong("frontman_loc_x"),
                            rs.getDouble("frontman_loc_y"),
                            rs.getString("frontman_loc_name")
                    );

                    Person frontMan = new Person(
                            rs.getString("frontman_name"),
                            Color.valueOf(rs.getString("frontman_eye_color")),
                            Color.valueOf(rs.getString("frontman_hair_color")),
                            location,
                            rs.getObject("frontman_birthday", LocalDate.class),
                            rs.getObject("frontman_height", Float.class),
                            rs.getString("frontman_nationality") != null ? Country.valueOf(rs.getString("frontman_nationality")) : null
                    );

                    return new MusicBand(
                            rs.getLong("id"),
                            rs.getString("name"),
                            coords,
                            rs.getObject("creation_date", LocalDate.class),
                            rs.getString("genre") != null ? MusicGenre.valueOf(rs.getString("genre")) : null,
                            rs.getInt("number_of_participants"),
                            rs.getLong("singles_count"),
                            rs.getString("description"),
                            album,
                            rs.getLong("albums_count"),
                            rs.getObject("establishment_date", LocalDate.class),
                            frontMan
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
