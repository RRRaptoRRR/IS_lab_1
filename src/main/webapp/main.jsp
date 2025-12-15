<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Beans.ResultsBean" %>
<%@ page import="Data.MusicBand" %>

<html>
    <head>
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

        <link rel="stylesheet" href="resources/css/main.css">

        <meta charset="UTF-8">

</head>
<body>
<h2>Music Bands!</h2>



<div class="filter-panel">
    <!-- Левый блок -->
    <div class="filter-block">
        <label>Поиск по ID</label>
        <input id="searchId" oninput="clearOtherFilters('searchId'); filterById();" placeholder="Введите ID...">

        <label>Поиск по названию</label>
        <input id="searchName" oninput="clearOtherFilters('searchName'); filterByName();" placeholder="Введите имя...">
    </div>

    <!-- Правый блок -->
    <div class="filter-block">
        <div style="display: flex; justify-content: space-between; align-items: center;">
            <label>Дата основания</label>
            <span style="font-size: 12px; color: #777;">Найдено: <span id="dateCount">0</span></span>
        </div>
        <input id="searchDate" type="date" oninput="clearOtherFilters('searchDate'); filterByDate();">

        <div style="display: flex; justify-content: space-between; align-items: center;">
            <label>Возраст фронтмена</label>
            <span style="font-size: 12px; color: #777;">Найдено: <span id="ageCount">0</span></span>
        </div>
        <input id="searchAge" type="number" oninput="clearOtherFilters('searchAge'); filterByAge();" placeholder="Макс. возраст...">
    </div>
</div>





<div>
    <form action="createBand" method="get" style="text-align: center;">
        <button  type="submit" >Создать новую банду</button>
    </form>
</div>

<div id="list">
    <table id="result_table" border="1" cellpadding="0" cellspacing="0" width="100%" class="results">
        <thead>
        <tr>
            <th rowspan="3"> ID </th>
            <th rowspan="3"> Имя группы </th>
            <th colspan="2"> Координаты </th>
            <th rowspan="3"> Дата создания объекта </th>
            <th rowspan="3"> Жанр </th>
            <th rowspan="3"> Количество участников </th>
            <th rowspan="3"> Количество синглов</th>
            <th rowspan="3"> Описание группы</th>
            <th colspan="2"> Лучший альбом </th>
            <th rowspan="3"> Количество альбомов</th>
            <th rowspan="3"> Дата Основания группы </th>
            <th colspan="9"> Руковадитель группы </th>

            <th rowspan="3">Действия</th>

        </tr>
        <tr>
            <th rowspan="2"> X </th>
            <th rowspan="2"> Y </th>

            <th rowspan="2"> Название альбома</th>
            <th rowspan="2"> Продажи альбома</th>

            <th rowspan="2"> Имя </th>
            <th rowspan="2"> Цвет глаз</th>
            <th rowspan="2"> Цвет волос</th>
            <th colspan="3"> Место рождения</th>
            <th rowspan="2"> Дата рождения</th>
            <th rowspan="2"> Рост</th>
            <th rowspan="2"> Национальность</th>
        </tr>
        <tr>
            <th> X </th>
            <th> Y </th>
            <th> Название </th>
        </tr>
        </thead>
        <tbody id="musicTableBody">
        <!--
           При первой загрузке страницы сервер сразу вставит сюда данные через include.
           Это нужно, чтобы пользователь не ждал первую секунду пустую таблицу.
        -->
        <jsp:include page="table-rows.jsp"/>
        </tbody>
    </table>
</div>
<div style="margin: 10px 0; text-align: center;">
    <label for="rowsPerPageSelect">Показывать по:</label>
    <select id="rowsPerPageSelect" onchange="window.currentPage = 1; showPage(1);">
        <option value="2" selected>2</option>
        <option value="5">5</option>
        <option value="10">10</option>
        <option value="1000000">Все</option>
    </select>
</div>

<script type="text/javascript" src="resources/js/pagination.js"></script>
<script type="text/javascript" src="resources/js/filters.js"></script>

<script type="text/javascript" src="resources/js/table.js"></script>
<div id="pagination" style="margin-top: 15px; text-align: center;"></div>
</body>
</html>