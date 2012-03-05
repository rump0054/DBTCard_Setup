package logic;

import dao.CardDAO;
import entities.Card;
import entities.Target;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.vraptor.annotations.Component;
import org.vraptor.annotations.In;
import org.vraptor.annotations.Out;
import org.vraptor.annotations.Parameter;

@Component("card")
public class CardLogic {

    @In
    private HttpServletRequest request;
    @In
    private HttpSession session;
    @Parameter
    private String datekey;
    @Parameter
    private long targetID;
    @Parameter(create = true)
    private ArrayList<Target> targs;
    @Parameter(create = true)
    private Card dayCard;
    @Out
    private String keydate;
    @Out
    private Card card;
    @Out
    private String msgs;

    // Method determines if user goes to day or setup pages
    public String direct() {
        String username = "erumppe";
        keydate = datekey;

        // Remove card from session in case it was left by setup
        session.removeAttribute("card");

        boolean needCard = CardDAO.needCard(username, datekey);
        if (!needCard) {
            return "day";
        } else {
            return "setup";
        }
    }

    // Empty method that sends you to the month calendar page
    public void month() {
    }

    // Method determines if forward to card setup or day page
    public String day() {
        String username = "erumppe";  //request.getRemoteUser();

        card = new Card();
        card = CardDAO.getCard(username, datekey);
        card.setDatekey(datekey);

        return "ok";
    }

    public void setup()
    {
        String username = "erumppe";
        
        if(session.getAttribute("card") != null &&
           session.getAttribute("card") != "")
        {
            card = (Card)session.getAttribute("card");
            card.setDatekey(datekey);
        }
        else
        {
            card = new Card();
            card.setUsername(username);
            card.setWeekStart(datekey);
            card.setDatekey(datekey);
            
            card = CardDAO.setupCard(card);
            
            session.setAttribute("card", card);
        }
    }

    // Disable a target from the card setup screen
    public void disableTarget() {
        card = new Card();
        card = (Card) session.getAttribute("card");

        ArrayList<Target> targets = new ArrayList<Target>();
        for (Target t : card.getTargets()) {
            if (t.getTargetID() != targetID) {
                targets.add(t);
            } else {
                t.setActive(false);
                targets.add(t);
            }
        }

        Collections.sort(targets);

        card.setTargets(targets);
        session.setAttribute("card", card);
    }

    // Enable a target from the card setup screen
    public void enableTarget() {
        card = new Card();
        card = (Card) session.getAttribute("card");

        ArrayList<Target> targets = new ArrayList<Target>();
        for (Target t : card.getTargets()) {
            if (t.getTargetID() != targetID) {
                targets.add(t);
            } else {
                t.setActive(true);
                targets.add(t);
            }
        }

        Collections.sort(targets);

        card.setTargets(targets);
        session.setAttribute("card", card);
    }

    // Add a target to the database and the card setup screen
    public void addTarget(Target target) {
        card = new Card();
        card = (Card) session.getAttribute("card");
        ArrayList<Target> targets = card.getTargets();

        if (target != null && !target.getTarget().equals("") && !target.getCategory().equals("")) {
            target.setUsername(card.getUsername());
            target = CardDAO.insertTarget(target);
            target.setActive(true);

            targets.add(target);
        } else {
            msgs = "An error occurred.  Please try again and make sure you enter "
                    + "information in all the fields.";
        }

        Collections.sort(targets);

        card.setTargets(targets);

        session.setAttribute("card", card);
    }

    // Add card to the database and goto day view for datekey
    public void addCard() {
        card = new Card();
        card = (Card) session.getAttribute("card");

        CardDAO.insertCard(card);

        // Remember to add to DB only ACTIVE targets
        session.removeAttribute("card");
    }

    public void addEntry() {
        keydate = dayCard.getDatekey();

        CardDAO.deleteDayValues(dayCard.getCardID(), dayCard.getDaykey());

        for (Target t : targs) {
            if (t.getValue() != null && !t.getValue().equals("")) {
                CardDAO.insertDayValues(t);
            }
        }
    }
}