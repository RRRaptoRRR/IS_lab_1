package Beans;

import Data.MusicBand.MusicBand;
import Data.MusicBand.MusicGenre;
import Data.MusicBand.Person;
import DataBase.DatabaseHandler;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ResultsBean implements Serializable {

    // Это поле теперь временное, оно перезаполняется при каждом вызове getResult()
    private ArrayList<MusicBand> result;

    public ResultsBean() {
        this.result = new ArrayList<>();
    }

    /**
     * Основной метод: идет в БД, делает SELECT и возвращает заполненный ArrayList.
     */
    public ArrayList<MusicBand> getResult() {
        // Очищаем старый список перед новой загрузкой
        this.result = new ArrayList<>();

        // SQL запрос ко всем полям
        String sql = "SELECT * FROM music_bands ORDER BY id ASC";

        try (Connection connection = DatabaseHandler.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // 1. Извлекаем координаты
                Coordinates coordinates = new Coordinates(
                        rs.getFloat("coord_x"),
                        rs.getFloat("coord_y")
                );

                // 2. Извлекаем альбом (может быть null)
                Album bestAlbum = null;
                String albumName = rs.getString("best_album_name");
                if (albumName != null) {
                    bestAlbum = new Album(
                            albumName,
                            rs.getDouble("best_album_sales")
                    );
                }

                // 3. Извлекаем локацию фронтмена
                Location location = new Location(
                        rs.getLong("frontman_loc_x"),
                        rs.getDouble("frontman_loc_y"),
                        rs.getString("frontman_loc_name")
                );

                // 4. Извлекаем данные фронтмена
                // Безопасное чтение Enum (если в базе NULL или мусор, надо обрабатывать, но пока считаем что там всё ок)
                String nationalityStr = rs.getString("frontman_nationality");
                Country nationality = (nationalityStr != null) ? Country.valueOf(nationalityStr) : null;

                // Безопасное чтение высоты (может быть null в базе)
                Float height = rs.getObject("frontman_height", Float.class);

                Person frontMan = new Person(
                        rs.getString("frontman_name"),
                        Color.valueOf(rs.getString("frontman_eye_color")),
                        Color.valueOf(rs.getString("frontman_hair_color")),
                        location,
                        rs.getObject("frontman_birthday", LocalDate.class),
                        height,
                        nationality
                );

                // 5. Извлекаем жанр (может быть null)
                String genreStr = rs.getString("genre");
                MusicGenre genre = (genreStr != null) ? MusicGenre.valueOf(genreStr) : null;

                // 6. Собираем основной объект MusicBand
                MusicBand band = new MusicBand(
                        rs.getLong("id"),
                        rs.getString("name"),
                        coordinates,
                        rs.getObject("creation_date", LocalDate.class),
                        genre,
                        rs.getInt("number_of_participants"),
                        rs.getLong("singles_count"),
                        rs.getString("description"),
                        bestAlbum,
                        rs.getLong("albums_count"),
                        rs.getObject("establishment_date", LocalDate.class),
                        frontMan
                );

                // Добавляем в список
                result.add(band);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // В реальном приложении можно бросить RuntimeException, чтобы увидеть ошибку на странице
        }

        return result;
    }

    public void setResult(ArrayList<MusicBand> result) {
        this.result = result;
    }

    /**
     * Этот метод можно оставить для обратной совместимости,
     * но по факту добавление теперь должно идти через INSERT в базу,
     * а не через этот метод.
     */
    public void addMusicBandToResult(MusicBand musicBand) {
        // Если вы хотите, чтобы этот метод тоже писал в базу,
        // сюда нужно перенести код INSERT из контроллера.
        // Но сейчас логичнее оставить его пустым или кидать ошибку,
        // так как добавление у вас происходит в CreateBandController.
    }
}
