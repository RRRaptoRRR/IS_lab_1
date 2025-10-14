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

@WebServlet("/")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        ResultsBean resultsBean = (ResultsBean) session.getAttribute("table");
        if (resultsBean == null) {
            resultsBean = new ResultsBean();
        }
        MusicBand musicBand = new MusicBand((long) 0, "nirvana", new Coordinates( (float) 0.2 , (float) 0.1), LocalDate.now(), MusicGenre.ROCK, 4, 10, "Yeeehh", new Album("Deluxe", 100000.00), 5, LocalDate.now(), new Person("Kobein", Color.GREEN, Color.YELLOW, new Location( (long) 1.1, 0.0, "Arizona"), LocalDate.now(), (float) 179, Country.USA));
        resultsBean.addMusicBandToResult(musicBand);
        session.setAttribute("table", resultsBean);
        getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
    }
}