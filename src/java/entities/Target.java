package entities;

import java.util.ArrayList;

public class Target implements Comparable<Target> {

    protected long targetID;
    protected long cardID;
    protected int dayKey;
    protected String username;
    protected String target;
    protected String description;
    protected String rangeMax;
    protected String category;
    protected String value;
    protected ArrayList<String> weekValues = new ArrayList<String>();
    protected boolean active;

    public Target() {
    }

    public long getCardID() {
        return cardID;
    }

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }

    public ArrayList<String> getWeekValues() {
        return weekValues;
    }

    public void setWeekValues(ArrayList<String> weekValues) {
        this.weekValues = weekValues;
    }

    public void addWeekValue(String weekValue) {
        this.weekValues.add(weekValue);
    }
        
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDayKey() {
        return dayKey;
    }

    public void setDayKey(int dayKey) {
        this.dayKey = dayKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(String rangeMax) {
        this.rangeMax = rangeMax;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public long getTargetID() {
        return targetID;
    }

    public void setTargetID(long targetID) {
        this.targetID = targetID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Target{" + "targetID=" + targetID + ", cardID=" + cardID + ", dayKey=" + dayKey + ", username=" + username + ", target=" + target + ", description=" + description + ", rangeMax=" + rangeMax + ", category=" + category + ", value=" + value + ", active=" + active + '}';
    }

    @Override
    public int compareTo(Target target) {
        String a = this.getTarget();
        String b = target.getTarget();
        
        return a.compareTo(b);
    }
}