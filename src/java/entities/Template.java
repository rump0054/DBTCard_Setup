package entities;

import java.util.ArrayList;

public class Template {

    /**
     * This attribute maps to the column templateID in the c_template table.
     */
    protected long templateID;
    /**
     * This attribute maps to the column name in the c_template table.
     */
    protected String name;
    /**
     * This attribute maps to the column uid in the c_template table.
     */
    protected String uid;
    protected ArrayList<Card> targets;

    /**
     * Method 'Template'
     *
     */
    public Template() {
    }

    /**
     * Method 'getTemplateID'
     *
     * @return long
     */
    public long getTemplateID() {
        return templateID;
    }

    /**
     * Method 'setTemplateID'
     *
     * @param templateID
     */
    public void setTemplateID(long templateID) {
        this.templateID = templateID;
    }

    /**
     * Method 'getName'
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method 'setName'
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method 'getUid'
     *
     * @return String
     */
    public String getUid() {
        return uid;
    }

    /**
     * Method 'setUid'
     *
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    public ArrayList<Card> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<Card> targets) {
        this.targets = targets;
    }

    public void addTarget(Card target) {
        this.targets.add(target);
    }

    /**
     * Method 'equals'
     *
     * @param _other
     * @return boolean
     */
    public boolean equals(Object _other) {
        if (_other == null) {
            return false;
        }

        if (_other == this) {
            return true;
        }

        if (!(_other instanceof Template)) {
            return false;
        }

        final Template _cast = (Template) _other;
        if (templateID != _cast.templateID) {
            return false;
        }

        if (name == null ? _cast.name != name : !name.equals(_cast.name)) {
            return false;
        }

        if (uid == null ? _cast.uid != uid : !uid.equals(_cast.uid)) {
            return false;
        }

        return true;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("dbtcard.dto.CTemplate: ");
        ret.append("templateID=" + templateID);
        ret.append(", name=" + name);
        ret.append(", uid=" + uid);
        return ret.toString();
    }
}