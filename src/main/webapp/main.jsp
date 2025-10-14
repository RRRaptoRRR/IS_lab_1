<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Beans.ResultsBean" %>
<%@ page import="Data.MusicBand" %>

<html>
<body>
<h2>Music Bands!</h2>
<% ResultsBean resultsBean = (ResultsBean) request.getSession().getAttribute("table");
    ArrayList<MusicBand> raws = resultsBean.getResult();%>

<div id = "list">
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
            <th colspan="10"> Руковадитель группы </th>

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
            <td><%= raw.getFrontMan().getNationality()%></td>v
        </tr>
        <%
                    }
                }
            }
        %>

        </tbody>
    </table>

</div>
</body>
</html>
