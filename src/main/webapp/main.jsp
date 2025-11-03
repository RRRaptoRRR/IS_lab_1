<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Beans.ResultsBean" %>
<%@ page import="Data.MusicBand" %>

<html>
<head>
    <script type="text/javascript" src="resources/js/filters.js">


    </script>
    <script type="text/javascript" src="resources/js/pagination.js"></script>
        <link rel="stylesheet" href="resources/css/styles.css">

    <meta charset="UTF-8">

</head>
<body>
<h2>Music Bands!</h2>
<% ResultsBean resultsBean = (ResultsBean) request.getSession().getAttribute("table");
    ArrayList<MusicBand> raws = resultsBean.getResult();%>





<div id = "filtert">
    <p>Поиск по ID</p>
    <input type="number" id="searchId" placeholder="Поиск по ID" oninput="filterById()" style="width: 200px;">

    <p>Поиск по названию группы</p>
    <input type="text" id="searchName" placeholder="Поиск по названию" oninput="filterByName()" style="width: 200px;">
    <br><br>

    <p>Фильтр по дате основания(основаны позже этой даты)</p>
    <input type="date" id="searchDate" oninput="filterByDate()" style="width: 200px;">
    <p>Количество найденных групп: <span id="dateCount">0</span></p>

    <p>Фильтр по возрасту фронтмена:</p>
    <input type="number" id="searchAge" placeholder="Возраст" oninput="filterByAge()" style="width: 200px;">
    <p>Количество найденных групп: <span id="ageCount">0</span></p>

</div>



<div>
    <form action="createBand" method="get">
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
        <tbody>
        <%
            if(raws!=null){
                if(!raws.isEmpty()){
                    for (MusicBand raw:raws){
        %>
        <tr>
            <td><%= raw.getId()%></td>
            <td><%= raw.getName()%></td>
            <td><%= raw.getCoordinates().getX()%></td>
            <td><%= raw.getCoordinates().getY()%></td>
            <td><%= raw.getCreationDate()%></td>
            <td><%= raw.getGenre()%></td>
            <td><%= raw.getNumberOfParticipants()%></td>
            <td><%= raw.getSinglesCount()%></td>
            <td><%= raw.getDescription()%></td>
            <td><%= raw.getBestAlbum().getName()%></td>
            <td><%= raw.getBestAlbum().getSales()%></td>
            <td><%= raw.getAlbumsCount()%></td>
            <td><%= raw.getEstablishmentDate()%></td>
            <td><%= raw.getFrontMan().getName()%></td>
            <td><%= raw.getFrontMan().getEyeColor()%></td>
            <td><%= raw.getFrontMan().getHairColor()%></td>
            <td><%= raw.getFrontMan().getLocation().getX()%></td>
            <td><%= raw.getFrontMan().getLocation().getY()%></td>
            <td><%= raw.getFrontMan().getLocation().getName()%></td>
            <td><%= raw.getFrontMan().getBirthday()%></td>
            <td><%= raw.getFrontMan().getHeight()%></td>
            <td><%= raw.getFrontMan().getNationality()%></td>
            <td>
                <!-- Кнопка редактирования -->
                <form action="editBand" method="get" style="display:inline">
                    <input type="hidden" name="id" value="<%= raw.getId()%>">
                    <button type="submit">Редактировать</button>
                </form>

                <!-- Кнопка удаления -->
                <form action="deleteBand" method="post" style="display:inline">
                    <input type="hidden" name="id" value="<%= raw.getId()%>">
                    <button type="submit">Удалить</button>
                </form>
            </td>

        </tr>
        <%
                    }
                }
            }
        %>
        </tbody>
    </table>
</div>

<div id="pagination" style="margin-top: 15px;"></div>

</body>
</html>