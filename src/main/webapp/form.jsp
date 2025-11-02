<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание музыкальной группы</title>
</head>
<body>
<h2>Создать новую группу</h2>

<form action="createBand" method="post">
    <label>Название группы:</label><br>
    <input type="text" name="name" required><br><br>

    <h3>Координаты:</h3>
    <label>X:</label><br>
    <input type="number" name="coordX" step="0.1" required><br>
    <label>Y:</label><br>
    <input type="number" name="coordY" step="0.1" required><br><br>

    <label>Жанр:</label><br>
    <select name="genre">
        <option value="">- Не указано -</option>
        <option value="ROCK">ROCK</option>
        <option value="PROGRESSIVE_ROCK">PROGRESSIVE_ROCK</option>
        <option value="HIP_HOP">HIP_HOP</option>
        <option value="BRIT_POP">BRIT_POP</option>
    </select><br><br>

    <label>Количество участников:</label><br>
    <input type="number" name="numberOfParticipants" min="1" required><br><br>

    <label>Количество синглов:</label><br>
    <input type="number" name="singlesCount" min="1" required><br><br>

    <label>Описание группы:</label><br>
    <textarea name="description" required></textarea><br><br>

    <h3>Лучший альбом:</h3>
    <label>Название альбома:</label><br>
    <input type="text" name="albumName"><br>
    <label>Продажи:</label><br>
    <input type="number" step="0.01" name="albumSales" min="0"><br><br>

    <label>Количество альбомов:</label><br>
    <input type="number" name="albumsCount" min="1" required><br><br>

    <label>Дата основания группы:</label><br>
    <input type="date" name="establishmentDate" required><br><br>

    <h3>Фронтмен:</h3>
    <label>Имя:</label><br>
    <input type="text" name="frontManName" required><br><br>

    <label>Цвет глаз:</label><br>
    <select name="eyeColor" required>
        <option value="GREEN">GREEN</option>
        <option value="YELLOW">YELLOW</option>
        <option value="BROWN">BROWN</option>
    </select><br><br>

    <label>Цвет волос:</label><br>
    <select name="hairColor" required>
        <option value="GREEN">GREEN</option>
        <option value="YELLOW">YELLOW</option>
        <option value="BROWN">BROWN</option>
    </select><br><br>

    <label>Дата рождения:</label><br>
    <input type="date" name="birthday" required><br><br>

    <label>Рост:</label><br>
    <input type="number" step="0.01" min="0.1" name="height"><br><br>

    <label>Национальность:</label><br>
    <select name="nationality">
        <option value="">- Не указано -</option>
        <option value="USA">USA</option>
        <option value="INDIA">INDIA</option>
        <option value="ITALY">ITALY</option>
    </select><br><br>

    <h3>Локация фронтмена:</h3>
    <label>X:</label><br>
    <input type="number" name="locX" required><br>
    <label>Y:</label><br>
    <input type="number" step="0.01" name="locY" required><br>
    <label>Название локации:</label><br>
    <input type="text" name="locName" required><br><br>

    <button type="submit">Создать</button>
</form>

</body>
</html>
