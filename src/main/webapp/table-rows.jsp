<%@ page import="Beans.ResultsBean" %>
<%@ page import="Data.MusicBand" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<% ArrayList<MusicBand> raws = (ArrayList<MusicBand>) request.getAttribute("bandsList");%>

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
