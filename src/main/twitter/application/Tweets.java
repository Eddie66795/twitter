package main.twitter.application;

import java.util.Calendar;
import java.util.Date;

public class Tweets {

    private String textValue;
    private String owner;
    private Date dateTime;
    private String violationMessage;

    public Tweets(final String owner, final String textValue) {
        this.owner = owner;
        this.textValue = textValue;
        this.dateTime = getCurrentDateTime();
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Date getCurrentDateTime() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    public boolean isValid() {
        if (owner == null || owner.isEmpty()) {
            setViolationMessage("VIOLATION: OWNER FIELD EMPTY OR NULL");
            return false;
        }

        if (dateTime == null) {
            setViolationMessage("VIOLATION: DATE FIELD NOT SET");
            return false;
        }

        if(textValue.length() > 140) {
            setViolationMessage("VIOLATION: TEXTVALUE > 140 CHARS");
            return false;
        }

        return true;
    }

    public String getTextValue() {
        return textValue;
    }

    public String getOwner() {
        return owner;
    }

    public void setViolationMessage(String violationMessage) {
        this.violationMessage = violationMessage;
    }

    public String getViolationMessage() {
        return violationMessage;
    }

    public String getFormattedTextValue() {
        return "@".concat(owner).concat(": ").concat(textValue);
    }
}
