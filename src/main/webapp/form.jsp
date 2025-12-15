<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>${band != null ? "Редактирование" : "Создание"}</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/css/form.css">
</head>
<body>

<div class="form-container">
    <h2>${band != null ? "Редактирование группы" : "Создание новой группы"}</h2>

    <form action="${band != null ? 'editBand' : 'createBand'}" method="post" class="styled-form">

        <input type="hidden" name="id" value="${band != null ? band.id : ''}">

        <!-- Основная информация -->
        <fieldset>
            <legend>Основная информация</legend>
            <div class="form-group">
                <label>Название группы</label>
                <input type="text" name="name" value="${band != null ? band.name : ''}" required>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Жанр</label>
                    <select name="genre">
                        <option value="" ${band != null && band.genre == null ? "selected" : ""}>Не указано</option>
                        <option value="ROCK" ${band != null && band.genre == 'ROCK' ? "selected" : ""}>ROCK</option>
                        <option value="PROGRESSIVE_ROCK" ${band != null && band.genre == 'PROGRESSIVE_ROCK' ? "selected" : ""}>PROGRESSIVE_ROCK</option>
                        <option value="HIP_HOP" ${band != null && band.genre == 'HIP_HOP' ? "selected" : ""}>HIP_HOP</option>
                        <option value="BRIT_POP" ${band != null && band.genre == 'BRIT_POP' ? "selected" : ""}>BRIT_POP</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Дата основания</label>
                    <input type="date" name="establishmentDate" value="${band != null ? band.establishmentDate : ''}" required>
                </div>
            </div>
            <div class="form-group">
                <label>Описание</label>
                <textarea name="description" rows="3" required>${band != null ? band.description : ""}</textarea>
            </div>
        </fieldset>

        <!-- Статистика -->
        <fieldset>
            <legend>Статистика и Состав</legend>
            <div class="form-row">
                <div class="form-group">
                    <label>Участников</label>
                    <input type="number" name="numberOfParticipants" value="${band != null ? band.numberOfParticipants : ''}" required>
                </div>
                <div class="form-group">
                    <label>Синглов</label>
                    <input type="number" name="singlesCount" value="${band != null ? band.singlesCount : ''}" required>
                </div>
                <div class="form-group">
                    <label>Альбомов</label>
                    <input type="number" name="albumsCount" value="${band != null ? band.albumsCount : ''}" required>
                </div>
            </div>
        </fieldset>

        <!-- Координаты -->
        <fieldset>
            <legend>Координаты</legend>
            <div class="form-row">
                <div class="form-group">
                    <label>X</label>
                    <input type="number" step="0.1" name="coordX" value="${band != null ? band.coordinates.x : ''}" required>
                </div>
                <div class="form-group">
                    <label>Y</label>
                    <input type="number" step="0.1" name="coordY" value="${band != null ? band.coordinates.y : ''}" required>
                </div>
            </div>
        </fieldset>

        <!-- Лучший альбом -->
        <fieldset>
            <legend>Лучший альбом</legend>
            <div class="form-row">
                <div class="form-group">
                    <label>Название</label>
                    <input type="text" name="albumName" value="${band != null && band.bestAlbum != null ? band.bestAlbum.name : ''}">
                </div>
                <div class="form-group">
                    <label>Продажи</label>
                    <input type="number" step="0.01" name="albumSales" value="${band != null && band.bestAlbum != null && band.bestAlbum.sales != null ? band.bestAlbum.sales : ''}">
                </div>
            </div>
        </fieldset>

        <!-- Фронтмен -->
        <fieldset>
            <legend>Фронтмен</legend>
            <div class="form-group">
                <label>Имя</label>
                <input type="text" name="frontManName" value="${band != null ? band.frontMan.name : ''}" required>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Дата рождения</label>
                    <input type="date" name="birthday" value="${band != null ? band.frontMan.birthday : ''}" required>
                </div>
                <div class="form-group">
                    <label>Рост</label>
                    <input type="number" step="0.1" name="height" value="${band != null && band.frontMan.height != null ? band.frontMan.height : ''}">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Глаза</label>
                    <select name="eyeColor" required>
                        <option value="GREEN" ${band != null && band.frontMan.eyeColor == 'GREEN' ? "selected" : ""}>GREEN</option>
                        <option value="YELLOW" ${band != null && band.frontMan.eyeColor == 'YELLOW' ? "selected" : ""}>YELLOW</option>
                        <option value="BROWN" ${band != null && band.frontMan.eyeColor == 'BROWN' ? "selected" : ""}>BROWN</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Волосы</label>
                    <select name="hairColor" required>
                        <option value="GREEN" ${band != null && band.frontMan.hairColor == 'GREEN' ? "selected" : ""}>GREEN</option>
                        <option value="YELLOW" ${band != null && band.frontMan.hairColor == 'YELLOW' ? "selected" : ""}>YELLOW</option>
                        <option value="BROWN" ${band != null && band.frontMan.hairColor == 'BROWN' ? "selected" : ""}>BROWN</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Страна</label>
                    <select name="nationality">
                        <option value="" ${band != null && band.frontMan.nationality == null ? "selected" : ""}>Не указано</option>
                        <option value="USA" ${band != null && band.frontMan.nationality == 'USA' ? "selected" : ""}>USA</option>
                        <option value="INDIA" ${band != null && band.frontMan.nationality == 'INDIA' ? "selected" : ""}>INDIA</option>
                        <option value="ITALY" ${band != null && band.frontMan.nationality == 'ITALY' ? "selected" : ""}>ITALY</option>
                    </select>
                </div>
            </div>

            <p style="margin-bottom: 5px; font-weight: bold; font-size: 0.9em; color: #555;">Локация фронтмена:</p>
            <div class="form-row">
                <div class="form-group" style="flex: 2;">
                    <input type="text" name="locName" placeholder="Название локации" value="${band != null ? band.frontMan.location.name : ''}" required>
                </div>
                <div class="form-group">
                    <input type="number" name="locX" placeholder="X" value="${band != null ? band.frontMan.location.x : ''}" required>
                </div>
                <div class="form-group">
                    <input type="number" step="0.01" name="locY" placeholder="Y" value="${band != null ? band.frontMan.location.y : ''}" required>
                </div>
            </div>
        </fieldset>

        <div class="form-actions">
            <button type="button" class="btn-cancel" onclick="goBack()">Отмена</button>
            <button type="submit" class="btn-submit">${band != null ? "Сохранить" : "Создать"}</button>
        </div>
    </form>
</div>

<script>
    function goBack() {
        window.location.href = "controller";
    }
</script>

</body>
</html>
