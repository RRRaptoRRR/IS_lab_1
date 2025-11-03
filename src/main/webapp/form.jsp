<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>${band != null ? "Редактирование группы" : "Создание группы"}</title>
</head>

<body>

<h2>${band != null ? "Редактирование музыкальной группы" : "Создание новой музыкальной группы"}</h2>

<form action="${band != null ? 'editBand' : 'createBand'}" method="post">

    <!-- Если редактируем — передаём id -->
    <input type="hidden" name="id" value="${band != null ? band.id : ''}">

    <label>Название группы:</label><br>
    <input type="text" name="name" value="${band != null ? band.name : ''}" required><br><br>

    <h3>Координаты:</h3>
    <label>X:</label><br>
    <input type="number" step="0.1" name="coordX" value="${band != null ? band.coordinates.x : ''}" required><br>
    <label>Y:</label><br>
    <input type="number" step="0.1" name="coordY" value="${band != null ? band.coordinates.y : ''}" required><br><br>

    <label>Жанр:</label><br>
    <select name="genre">
        <option value="" ${band != null && band.genre == null ? "selected" : ""}>Не указано</option>
        <option value="ROCK" ${band != null && band.genre == 'ROCK' ? "selected" : ""}>ROCK</option>
        <option value="PROGRESSIVE_ROCK" ${band != null && band.genre == 'PROGRESSIVE_ROCK' ? "selected" : ""}>PROGRESSIVE_ROCK</option>
        <option value="HIP_HOP" ${band != null && band.genre == 'HIP_HOP' ? "selected" : ""}>HIP_HOP</option>
        <option value="BRIT_POP" ${band != null && band.genre == 'BRIT_POP' ? "selected" : ""}>BRIT_POP</option>
    </select><br><br>

    <label>Количество участников:</label><br>
    <input type="number" name="numberOfParticipants" value="${band != null ? band.numberOfParticipants : ''}" required><br><br>

    <label>Количество синглов:</label><br>
    <input type="number" name="singlesCount" value="${band != null ? band.singlesCount : ''}" required><br><br>

    <label>Описание:</label><br>
    <textarea name="description" required>${band != null ? band.description : ""}</textarea><br><br>

    <h3>Лучший альбом:</h3>
    <label>Название:</label><br>
    <input type="text" name="albumName" value="${band != null && band.bestAlbum != null ? band.bestAlbum.name : ''}"><br>
    <label>Продажи:</label><br>
    <input type="number" step="0.01" name="albumSales" value="${band != null && band.bestAlbum != null && band.bestAlbum.sales != null ? band.bestAlbum.sales : ''}"><br><br>

    <label>Количество альбомов:</label><br>
    <input type="number" name="albumsCount" value="${band != null ? band.albumsCount : ''}" required><br><br>

    <label>Дата основания:</label><br>
    <input type="date" name="establishmentDate" value="${band != null ? band.establishmentDate : ''}" required><br><br>

    <h3>Фронтмен:</h3>
    <label>Имя:</label><br>
    <input type="text" name="frontManName" value="${band != null ? band.frontMan.name : ''}" required><br><br>

    <label>Цвет глаз:</label><br>
    <select name="eyeColor" required>
        <option value="GREEN" ${band != null && band.frontMan.eyeColor == 'GREEN' ? "selected" : ""}>GREEN</option>
        <option value="YELLOW" ${band != null && band.frontMan.eyeColor == 'YELLOW' ? "selected" : ""}>YELLOW</option>
        <option value="BROWN" ${band != null && band.frontMan.eyeColor == 'BROWN' ? "selected" : ""}>BROWN</option>
    </select><br><br>

    <label>Цвет волос:</label><br>
    <select name="hairColor" required>
        <option value="GREEN" ${band != null && band.frontMan.hairColor == 'GREEN' ? "selected" : ""}>GREEN</option>
        <option value="YELLOW" ${band != null && band.frontMan.hairColor == 'YELLOW' ? "selected" : ""}>YELLOW</option>
        <option value="BROWN" ${band != null && band.frontMan.hairColor == 'BROWN' ? "selected" : ""}>BROWN</option>
    </select><br><br>

    <label>Дата рождения:</label><br>
    <input type="date" name="birthday" value="${band != null ? band.frontMan.birthday : ''}" required><br><br>

    <label>Рост:</label><br>
    <input type="number" step="0.1" name="height" value="${band != null && band.frontMan.height != null ? band.frontMan.height : ''}"><br><br>

    <label>Национальность:</label><br>
    <select name="nationality">
        <option value="" ${band != null && band.frontMan.nationality == null ? "selected" : ""}>Не указано</option>
        <option value="USA" ${band != null && band.frontMan.nationality == 'USA' ? "selected" : ""}>USA</option>
        <option value="INDIA" ${band != null && band.frontMan.nationality == 'INDIA' ? "selected" : ""}>INDIA</option>
        <option value="ITALY" ${band != null && band.frontMan.nationality == 'ITALY' ? "selected" : ""}>ITALY</option>
    </select><br><br>

    <h3>Локация фронтмена:</h3>
    <label>X:</label><br>
    <input type="number" name="locX" value="${band != null ? band.frontMan.location.x : ''}" required><br>
    <label>Y:</label><br>
    <input type="number" step="0.01" name="locY" value="${band != null ? band.frontMan.location.y : ''}" required><br>
    <label>Название локации:</label><br>
    <input type="text" name="locName" value="${band != null ? band.frontMan.location.name : ''}" required><br><br>

    <button type="submit">${band != null ? "Сохранить изменения" : "Создать"}</button>

</form>

</body>
</html>
