package Controllers;

import Beans.ResultsBean;
import Data.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "/controller", urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        ResultsBean resultsBean = (ResultsBean) session.getAttribute("table");
        if (resultsBean == null) {
            resultsBean = new ResultsBean();
            MusicBand Nirvana = new MusicBand((long) 0, "nirvana", new Coordinates( (float) 0.2 , (float) 0.1), LocalDate.now(), MusicGenre.ROCK, 4, 10, "Yeeehh", new Album("Deluxe", 100000.00), 5, LocalDate.now(), new Person("Kobein", Color.GREEN, Color.YELLOW, new Location( (long) 1.1, 0.0, "Arizona"), LocalDate.now(), (float) 179, Country.USA));
            MusicBand Linkin_Park = new MusicBand((long) 1, "Linkn Park", new Coordinates( (float) 0.2 , (float) 0.1), LocalDate.now(), MusicGenre.ROCK, 4, 11, "Yeaahh", new Album("Hibrid Theory", 1100000.00), 7, LocalDate.now(), new Person("Maike Shinodu", Color.BROWN, Color.BROWN, new Location( (long) 1.1, 0.0, "London"), LocalDate.now(), (float) 180, Country.INDIA));
            MusicBand Artic_Monkeys = new MusicBand((long) 2, "Artic Monkeys", new Coordinates( (float) 0.2 , (float) 0.1), LocalDate.now(), MusicGenre.PROGRESSIVE_ROCK, 4, 11, "the holy Guitar", new Album("Favourite worst Nightmare", 10000.00), 7, LocalDate.now(), new Person("Alex Terner", Color.YELLOW, Color.YELLOW, new Location( (long) 1.1, 0.0, "Sheffild"), LocalDate.now(), (float) 178, Country.INDIA));

            resultsBean.addMusicBandToResult(Nirvana);
            resultsBean.addMusicBandToResult(Linkin_Park);
            resultsBean.addMusicBandToResult(Artic_Monkeys);
        }

        session.setAttribute("table", resultsBean);
        getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
    }
}