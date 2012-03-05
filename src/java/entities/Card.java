package entities;

import java.util.ArrayList;
import utils.DateUtils;

public class Card {
    private long cardID;
    private long previousCardID;
    private String username;
    private long weekStart;
    private ArrayList<Target> targets;
    private String datekey;
    private int daykey;

    public Card() {
    }

    public long getCardID() {
        return cardID;
    }

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }

    public long getPreviousCardID() {
        return previousCardID;
    }

    public void setPreviousCardID(long previousCardID) {
        this.previousCardID = previousCardID;
    }

    public ArrayList<Target> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<Target> targets) {
        this.targets = targets;
    }

    public void addTarget(Target target)
    {
        this.targets.add(target);
    }

    public ArrayList<Target> getCategoryTargets(String cat) {
        ArrayList<Target> feelings = new ArrayList<Target>();
        
        for(Target t : targets)
        {
            if(t.getCategory().equalsIgnoreCase(cat))
            {
                feelings.add(t);
            }
        }
        
        return feelings;
    }
        
    public ArrayList<Target> getActiveTargets(String cat) {
        ArrayList<Target> feelings = new ArrayList<Target>();
        
        for(Target t : targets)
        {
            if(t.getCategory().equalsIgnoreCase(cat) && t.isActive())
            {
                feelings.add(t);
            }
        }
        
        return feelings;
    }
 
    public ArrayList<Target> getInactiveTargets(String cat) {
        ArrayList<Target> feelings = new ArrayList<Target>();
        
        for(Target t : targets)
        {
            if(t.getCategory().equalsIgnoreCase(cat) && !t.isActive())
            {
                feelings.add(t);
            }
        }
        
        return feelings;
    }
        
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(long weekStart) {
        this.weekStart = weekStart;
    }

    public void setWeekStart(String datekey) {
        this.weekStart = DateUtils.getWeekStart(datekey);
    }

    public String getDatekey() {
        return datekey;
    }

    public void setDatekey(String datekey) {
        this.datekey = datekey;
    }

    public int getDaykey() {
        return daykey;
    }

    public void setDaykey(int daykey) {
        this.daykey = daykey;
    }

    @Override
    public String toString() {
        return "Card{" + "cardID=" + cardID + ", previousCardID=" + previousCardID + ", username=" + username + ", weekStart=" + weekStart + ", targets=" + targets + ", datekey=" + datekey + ", daykey=" + daykey + '}';
    }
}