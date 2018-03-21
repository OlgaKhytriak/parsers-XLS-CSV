package com.epam.fivethreads.data.model;

import com.epam.fivethreads.data.utils.parsersXLSandCSV.anotations.ParserElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "letter")
public class Letter implements Cloneable {
    @ParserElement("sent-to")
    @XmlElement(required = true, name = "sent-to")
    private String sentTo;

    private String messageSubject;

    @ParserElement("message-text")
    @XmlElement(required = true, name = "message-text")
    private String messageText;


    public Letter() {
    }

    public Letter(String sentTo, String messageSubject, String messageText) {
        this.setSentTo(sentTo);
        this.setMessageSubject(messageSubject);
        this.setMessageText(messageText);
    }

    public Letter copy() {
        Letter letter = new Letter();
        letter.setSentTo(this.sentTo);
        letter.setMessageSubject(this.messageSubject);
        letter.setMessageText(this.messageText);
        return letter;
    }

    @Override
    public Letter clone() {
        Object clonedObj = null;
        try {
            clonedObj = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Error in User.clone()");
            e.printStackTrace();
        }
        return (Letter) clonedObj;
    }

    public String toString() {
        return String.format(
                "[LETTER]: sent-to= %s ; message-subject= %s ; message-text= %s ",
                sentTo, messageSubject, messageText);
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
